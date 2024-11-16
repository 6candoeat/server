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

//    @Autowired
//    private DrugQueryService drugQueryService;
//
//    // 파일 업로드 페이지
//    @GetMapping("/upload")
//    public String upload() {
//        return "upload";
//    }
//
//    // csv 파일 업로드 처리
//    @PostMapping("/upload")
//    public String uploadFile(MultipartFile file, Model model) {
//        try {
//            String result = drugQueryService.saveDrugsFromCsv(file);
//            model.addAttribute("message", result);
//        } catch (Exception e) {
//            model.addAttribute("error", "파일 업로드 중 오류 발생: " + e.getMessage());
//        }
//        return "upload";
//    }
}
