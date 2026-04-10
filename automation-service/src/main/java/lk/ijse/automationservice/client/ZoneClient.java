package lk.ijse.automationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
@FeignClient(name = "zone-service") // Eureka වල තියෙන නම
public interface ZoneClient {
    @GetMapping("/api/zones/{id}")
    Map<String, Object> getZoneDetails(@PathVariable("id") String zoneId);
}
