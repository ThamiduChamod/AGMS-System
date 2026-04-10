package lk.ijse.automationservice.service.impl;

import lk.ijse.automationservice.client.ZoneClient;
import lk.ijse.automationservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j

public class AutomationServiceImpl implements AutomationService {

    private final ZoneClient zoneClient;

    public void processTelemetry(Map<String, Object> data) {
        String zoneId = (String) data.get("zoneId");
        Map<String, Object> values = (Map<String, Object>) data.get("value");
        Double currentTemp = (Double) values.get("temperature");

        // 1. Zone Service එකෙන් min/max limits අරගන්නවා (Inter-service communication)
        Map<String, Object> zoneDetails = zoneClient.getZoneDetails(zoneId);
        Double minTemp = (Double) zoneDetails.get("minTemp");
        Double maxTemp = (Double) zoneDetails.get("maxTemp");

        // 2. Decision Making (Rules)
        if (currentTemp > maxTemp) {
            log.info("ALERT: Current Temp {} > Max Temp {}. Action: TURN_FAN_ON", currentTemp, maxTemp);
            // මෙතනදී ඔබට Action එක DB එකේ save කරන්න පුළුවන්
        } else if (currentTemp < minTemp) {
            log.info("ALERT: Current Temp {} < Min Temp {}. Action: TURN_HEATER_ON", currentTemp, minTemp);
        } else {
            log.info("Temp is stable at {}°C in Zone {}", currentTemp, zoneId);
        }
    }
}
