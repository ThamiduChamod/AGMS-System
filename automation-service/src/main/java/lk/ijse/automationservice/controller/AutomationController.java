package lk.ijse.automationservice.controller;

import lk.ijse.automationservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/automation")
public class AutomationController {

    private final AutomationService automationService;

    @PostMapping("/process")
    public void receiveTelemetry(@RequestBody Map<String, Object> telemetryData) {
        automationService.processTelemetry(telemetryData);
    }
}
