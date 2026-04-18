package lk.ijse.cropservice.service;

import lk.ijse.cropservice.dto.CropDto;
import lk.ijse.cropservice.entity.Crop;

import java.util.List;

public interface CropService {
    void saveCrop(CropDto cropDto);
    List<Crop> getAllCrops();
    void deleteCrop(String cropCode);
    Crop getCropByCode(String cropCode);
    void updateCrop(CropDto cropDto);
}
