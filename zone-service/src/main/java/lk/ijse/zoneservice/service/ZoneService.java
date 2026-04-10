package lk.ijse.zoneservice.service;

import lk.ijse.zoneservice.entity.Zone;

public interface ZoneService {
    Zone createZone(Zone zone, String token);
}
