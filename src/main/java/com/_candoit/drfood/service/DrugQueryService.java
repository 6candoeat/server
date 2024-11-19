package com._candoit.drfood.service;

import com._candoit.drfood.domain.Drug;
import com._candoit.drfood.repository.DrugRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class DrugQueryService {

    @Autowired
    private DrugRepository drugRepository;

    public String getDiseaseCategory(Set<String> drugNames) {
        Map<String, Integer> diseaseCount = new HashMap<>();

        // 각 약품명으로 질병 카테고리 조회 및 카운트
        for (String drugName : drugNames) {
            Drug drug = drugRepository.findFirstByDrugNameContaining(drugName);

            if (drug != null) {
                String diseaseCategory = drug.getDiseaseCategory();

                // "고혈압, 당뇨"와 같이 여러 질병이 포함된 경우 분리하여 카운트
                String[] diseases = diseaseCategory.split(",");
                for (String disease : diseases) {
                    disease = disease.trim(); // 공백 제거
                    diseaseCount.put(disease, diseaseCount.getOrDefault(disease, 0) + 1);
                }
            }
        }

        // 가장 많이 사용된 질병 카테고리 찾기
        int maxCount = diseaseCount.values().stream().max(Integer::compareTo).orElse(0);

        // 최대 카운트를 가진 질병들 찾기
        List<String> topDiseases = diseaseCount.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .sorted() // 알파벳 순서로 정렬 (선택사항)
                .toList();

        // 질병을 ", "로 연결해서 반환
        return String.join(", ", topDiseases);
    }

    public int processCSV(MultipartFile file) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "CP949"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        int processedCount = 0;

        for (CSVRecord record : csvParser) {
            Long drugCode = Long.parseLong(record.get("drugCode"));
            String drugName = record.get("drugName");
            String companyName = record.get("companyName");
            String drugCategory = record.get("drugCategory");
            String diseaseCategory = record.get("diseaseCategory");

            // 기존 데이터 확인
            Optional<Drug> existingDrug = drugRepository.findByDrugCode(drugCode);
            if (existingDrug.isPresent()) {
                // 데이터가 이미 존재하면 diseaseCategory 업데이트
                Drug drug = existingDrug.get();
                if (!drug.getDiseaseCategory().contains(diseaseCategory)) {
                    drug.setDiseaseCategory(drug.getDiseaseCategory() + "," + diseaseCategory);
                    drugRepository.save(drug);
                }
            } else {
                // 새 데이터 저장
                Drug newDrug = new Drug();
                newDrug.setDrugCode(drugCode);
                newDrug.setDrugName(drugName);
                newDrug.setCompanyName(companyName);
                newDrug.setDrugCategory(drugCategory);
                newDrug.setDiseaseCategory(diseaseCategory);
                drugRepository.save(newDrug);
            }
            processedCount++;
        }

        csvParser.close();
        return processedCount;
    }
}
