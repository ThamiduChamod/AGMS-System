package lk.ijse.zoneservice.controller;

import lk.ijse.zoneservice.entity.Zone;
import lk.ijse.zoneservice.service.ZoneService;
import lk.ijse.zoneservice.service.impl.ZoneImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ZoneController {
    private final ZoneService zoneService;

    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(zoneService.createZone(zone, token));
    }


}
