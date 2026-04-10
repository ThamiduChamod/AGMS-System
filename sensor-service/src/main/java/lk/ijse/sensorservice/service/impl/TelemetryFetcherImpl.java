package lk.ijse.sensorservice.service.impl;

import lk.ijse.sensorservice.client.AutomationClient;
import lk.ijse.sensorservice.client.IoTClient;
import lk.ijse.sensorservice.service.TelemetryFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelemetryFetcherImpl implements TelemetryFetcher {

    private final IoTClient ioTClient;
    private final AutomationClient automationClient;

    private String accessToken;

    // තත්පර 10කට වරක් ක්‍රියාත්මක වේ
    @Scheduled(fixedRate = 10000)
    public void fetchAndPushData() {
        try {
            // 1. Login වී Token එක ලබා ගැනීම (පළමු වරට පමණක් හෝ Token එක කල් ඉකුත් වූ විට)
            if (accessToken == null) {
                Map<String, String> creds = new HashMap<>();
                creds.put("username", "your_username"); // Assignment එකේ Login එක
                creds.put("password", "your_password");
                Map<String, Object> response = ioTClient.login(creds);
                accessToken = (String) response.get("accessToken");
            }

            // 2. Device Telemetry ලබා ගැනීම 
            // සටහන: මෙහිදී ඔබට ලබා ගැනීමට අවශ්‍ය deviceId එක කලින් ලබාගෙන තිබිය යුතුය
            String deviceId = "b751b8c9-644a-484c-ba3f-be63f9b27ad0";
            Map<String, Object> telemetry = ioTClient.getTelemetry("Bearer " + accessToken, deviceId);

            log.info("Fetched Telemetry: {}", telemetry);

            // 3. Automation Service එකට දත්ත යැවීම (Pusher)
            automationClient.sendDataToProcess(telemetry);
            log.info("Data pushed to Automation Service successfully.");

        } catch (Exception e) {
            log.error("Error in Telemetry Task: {}", e.getMessage());
        }
    }
}
