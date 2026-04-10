package lk.ijse.automationservice.service;

import java.util.Map;

public interface AutomationService {
    void processTelemetry(Map<String, Object> data);
}
