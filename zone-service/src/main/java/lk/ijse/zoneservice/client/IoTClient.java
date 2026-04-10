package lk.ijse.zoneservice.client;

import lk.ijse.zoneservice.dto.DeviceResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "iot-external-service", url = "http://104.211.95.241:8080/api")
public interface IoTClient {
    @PostMapping("/devices")
    DeviceResponseDTO registerDevice(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> payload);
}
