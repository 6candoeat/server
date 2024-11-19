package com._candoit.drfood.controller;

import com._candoit.drfood.service.GoogleVisionOCR;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ocr")
@RequiredArgsConstructor
public class GoogleVisionOCRController {

    @Autowired
    private GoogleVisionOCR googleVisionOCR;

    @PostMapping("/parseImage")
    public ResponseEntity<String> extractText(@RequestParam("imageUrl") String imageUrl) {
        try {
            String mostFrequentDisease = googleVisionOCR.execute(imageUrl);
            return ResponseEntity.ok(mostFrequentDisease);
        } catch (Exception e) {
            //e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
