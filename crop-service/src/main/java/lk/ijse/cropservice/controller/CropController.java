package lk.ijse.cropservice.controller;

import lk.ijse.cropservice.dto.CropDto;
import lk.ijse.cropservice.entity.Crop;
import lk.ijse.cropservice.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCrop(@RequestBody CropDto cropDto) {
        cropService.saveCrop(cropDto);
        return ResponseEntity.ok("Crop Saved Successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Crop>> getAllCrops() {
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCrop(@RequestBody CropDto cropDto) {
        cropService.updateCrop(cropDto);
        return ResponseEntity.ok("Crop Updated Successfully!");
    }

    @DeleteMapping("/delete/{cropCode}")
    public ResponseEntity<String> deleteCrop(@PathVariable String cropCode) {
        cropService.deleteCrop(cropCode);
        return ResponseEntity.ok("Crop Deleted Successfully!");
    }

    @GetMapping("/{cropCode}")
    public ResponseEntity<Crop> getCrop(@PathVariable String cropCode) {
        return ResponseEntity.ok(cropService.getCropByCode(cropCode));
    }
}
