package lk.ijse.zoneservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String zoneId; // අපිට කැමති ID එකක් දිය හැක (e.g., "ZONE-001")

    private String name;
    private Double minTemp;
    private Double maxTemp;
    private String deviceId; // මෙය පසුව IoT API එකෙන් ලැබෙන අගයයි

}
