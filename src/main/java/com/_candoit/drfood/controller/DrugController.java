package com._candoit.drfood.controller;

import com._candoit.drfood.service.DrugQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DrugController {

    @Autowired
    private DrugQueryService drugQueryService;

    // 업로드 페이지
    @GetMapping("/uploadFile")
    public String showUploadPage() {
        return "uploadFile";
    }

    @PostMapping("/processFile")
    public String processFile(MultipartFile file, Model model) {
        try {
            int processedRecords = drugQueryService.processCSV(file);
            model.addAttribute("message", "파일 처리 완료: 총 " + processedRecords + "개의 기록이 처리되었습니다.");
        } catch (Exception e) {
            model.addAttribute("error", "파일 처리 중 오류 발생: " + e.getMessage());
        }
        return "uploadFile";
    }
}
