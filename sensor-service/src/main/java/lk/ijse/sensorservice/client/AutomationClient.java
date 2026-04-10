package lk.ijse.sensorservice.client;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
@FeignClient(name = "automation-service") // Eureka හරහා සම්බන්ධ වේ
public interface AutomationClient {
    @PostMapping("/api/automation/process")
    void sendDataToProcess(@RequestBody Map<String, Object> telemetryData);
}
