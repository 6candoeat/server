package com._candoit.drfood.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GoogleVisionOCR {

    @Autowired
    private DrugQueryService drugQueryService;

    public String execute(String imageUrl) throws IOException {
        StopWatch totalTime = new StopWatch();
        totalTime.start();

        // 이미지 로드
        URL url = new URL(imageUrl);
        ByteString imgBytes;
        try (InputStream in = url.openStream()) {
            imgBytes = ByteString.readFrom(in);
        }

        // Vision API 요청 생성
        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feat)
                .setImage(img)
                .build();
        List<AnnotateImageRequest> requests = new ArrayList<>();
        requests.add(request);

        // 텍스트 및 위치 정보 저장
        StringBuilder stringBuilder = new StringBuilder();

        Set<String> uniqueLines = new HashSet<>();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);

            for (AnnotateImageResponse res : response.getResponsesList()) {
                if (res.hasError()) {
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    return "Error detected";
                }

                String fullText = res.getFullTextAnnotation().getText();
                String[] lines = fullText.split("\n");

                // 약품명 필터링 조건 적용
                for (String line : lines) {
                    // 약품명이 "정", "캡슐", "액", "주", "원", "환"으로 끝나는 경우
                    if (line.matches(".*(정|캡슐|액|주|원|환)$")) {
                        uniqueLines.add(line.trim()); // HashSet에 추가
                    }
                    // 위 내용으로 끝나지 않는 경우, 뒤에 숫자 또는 "("가 있는 경우
                    else if (line.matches(".*(정|캡슐|액|주|원|환)(\\s*\\(|\\s*\\d+).*")) {
                        uniqueLines.add(line.trim()); // HashSet에 추가
                    }
                }
            }

            System.out.println(uniqueLines);
            // 약품명으로 가장 빈도 높은 질병 카테고리를 조회
            String mostFrequentDisease = drugQueryService.getDiseaseCategory(uniqueLines);


            totalTime.stop();
            System.out.println("총 시간 : " + totalTime.getTotalTimeMillis() + "ms");
            return mostFrequentDisease;
        }
    }
}
