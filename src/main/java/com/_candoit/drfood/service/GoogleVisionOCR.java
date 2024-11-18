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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            // 약품명 전처리
            Set<String> preprocessedLines = preprocessDrugNames(uniqueLines);

            System.out.println("preprocessedLines = " + preprocessedLines);

            // 약품명으로 가장 빈도 높은 질병 카테고리를 조회
            String mostFrequentDisease = drugQueryService.getDiseaseCategory(preprocessedLines);


            totalTime.stop();
            System.out.println("총 시간 : " + totalTime.getTotalTimeMillis() + "ms");
            return mostFrequentDisease;
        }
    }

    private Set<String> preprocessDrugNames(Set<String> drugNames) {
        Set<String> processedNames = new HashSet<>();
        Pattern pattern = Pattern.compile(".*?(정|캡슐|액|주|원|환)"); // "정", "캡슐", "액", "주", "원", "환"으로 끝나는 부분 추출

        for (String drugName : drugNames) {
            // 숫자 + 공백 제거하고 약품명만 남기기
            String cleanedName = drugName.replaceAll("^\\d+\\s*", "").trim();

            Matcher matcher = pattern.matcher(cleanedName);

            if (matcher.find()) {
                String processedName = matcher.group(); // "정", "캡슐", "액", "주", "원", "환"으로 끝나는 부분만 추출
                if (!isInvalidDrugName(processedName)) { // 유효하지 않은 약품명 필터링
                    processedNames.add(processedName);
                }
            }
        }

        return processedNames;
    }

    private boolean isInvalidDrugName(String drugName) {
        // 쓸모 없는 약품명을 필터링
        Set<String> invalidNames = Set.of("1정", "1캡슐", "1액", "1주", "1원", "1환", "정", "캡슐", "액", "주", "원", "환");
        return invalidNames.contains(drugName); // 불필요한 데이터 제외
    }
}
