package lk.ijse.cropservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cropCode;

    @Column(name = "crop_common_name", nullable = false)
    private String cropCommonName;

    @Column(name = "crop_scientific_name")
    private String cropScientificName;

    @Column(name = "crop_image", columnDefinition = "LONGTEXT")
    private String cropImage; // මෙතන base64 string එකක් හෝ image URL එකක් තියාගන්න පුළුවන්

    @Column(name = "category")
    private String category;
}
