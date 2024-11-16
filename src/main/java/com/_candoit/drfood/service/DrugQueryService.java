package com._candoit.drfood.service;

import com._candoit.drfood.domain.Drug;
import com._candoit.drfood.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class DrugQueryService {

    @Autowired
    private DrugRepository drugRepository;

    public String getDiseaseCategory(Set<String> drugNames) {
        Map<String, Integer> diseaseCount = new HashMap<>();

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

        // 가장 많이 사용된 질병 카테고리 return
        return diseaseCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown Disease Category");
    }

//    public String saveDrugsFromCsv(MultipartFile file) throws Exception{
//        if (file.isEmpty()) {
//            throw new Exception("파일이 비어있습니다.");
//        }
//
//        Reader reader = new InputStreamReader(file.getInputStream());
//        Iterator<CSVRecord> records = CSVFOR
//    }
}
