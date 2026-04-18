package lk.ijse.cropservice.service.impl;

import lk.ijse.cropservice.entity.Crop;
import lk.ijse.cropservice.repository.CropRepository;
import lk.ijse.cropservice.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lk.ijse.cropservice.dto.CropDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;

    public void saveCrop(CropDto cropDto) {
        Crop crop = new Crop();
        crop.setCropCommonName(cropDto.getCropCommonName());
        crop.setCategory(cropDto.getCategory());
        crop.setCropScientificName(cropDto.getCropScientificName());
        crop.setCropImage(String.valueOf(cropDto.getCropImage()));


        cropRepository.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    // Get By ID
    public Crop getCropByCode(String cropCode) {
        return cropRepository.findById(cropCode)
                .orElseThrow(() -> new RuntimeException("Crop not found with id: " + cropCode));
    }

    // Update
    public void updateCrop(CropDto cropDto) {
        if (cropRepository.existsById(cropDto.getCropCode())) {
            Crop crop = new Crop(cropDto.getCropCode(), cropDto.getCropCommonName(),
                    cropDto.getCropScientificName(), cropDto.getCropImage(),
                    cropDto.getCategory());
            cropRepository.save(crop);
        } else {
            throw new RuntimeException("Cannot update, Crop not found!");
        }
    }

    // Delete
    public void deleteCrop(String cropCode) {
        if (cropRepository.existsById(cropCode)) {
            cropRepository.deleteById(cropCode);
        } else {
            throw new RuntimeException("Cannot delete, Crop not found!");
        }
    }
}
