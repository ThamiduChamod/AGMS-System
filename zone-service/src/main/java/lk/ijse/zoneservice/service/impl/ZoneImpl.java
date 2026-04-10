package lk.ijse.zoneservice.service.impl;

import lk.ijse.zoneservice.client.IoTClient;
import lk.ijse.zoneservice.dto.DeviceResponseDTO;
import lk.ijse.zoneservice.entity.Zone;
import lk.ijse.zoneservice.repository.ZoneRepository;
import lk.ijse.zoneservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ZoneImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final IoTClient ioTClient;

    public Zone createZone(Zone zone, String token) {
        // Validation: minTemp strictly less than maxTemp
        if (zone.getMinTemp() >= zone.getMaxTemp()) {
            throw new RuntimeException("Min temperature must be less than max temperature");
        }

        // External API එකට payload එක සකසමු
        Map<String, String> payload = new HashMap<>();
        payload.put("name", zone.getName() + "-Sensor");
        payload.put("zoneId", zone.getZoneId());

        // IoT API එකට කතා කරලා deviceId එක ගනිමු
        DeviceResponseDTO response = ioTClient.registerDevice("Bearer " + token, payload);

        // ලැබුණු deviceId එක save කරමු
        zone.setDeviceId(response.getDeviceId());
        return zoneRepository.save(zone);
    }
}
