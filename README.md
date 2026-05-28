# 🍽️ Dr.Food

> 고령자의 건강 상태를 고려한 맞춤형 공공 배달 플랫폼

</div>

---

## 📌 프로젝트 소개

고혈압, 당뇨, 통풍을 앓고 있는 고령자를 위한 맞춤형 공공 배달 플랫폼입니다.

사용자의 건강 상태를 기반으로 음식 위험도를 분석하고 안전한 메뉴를 추천합니다.

약 봉투 사진 한 장만 촬영하면 OCR을 통해 질병을 자동 인식하여 고령자도 쉽게 서비스를 이용할 수 있습니다.

### 서비스 흐름

```text
💊 약 봉투 촬영
      ↓
🔍 OCR 질병 분석
      ↓
🍱 음식 위험도 계산
      ↓
🎯 맞춤형 메뉴 추천
      ↓
🛵 주문 및 배달
```

### 개발 기간

**2024.09 ~ 2024.11 (2개월)**

### 개발 인원

**6명 (팀 리더)**

---

## ✨ 주요 기능

<details>
<summary><b>💊 약 봉투 OCR 기반 질병 자동 등록</b></summary>

<br>

Google Cloud Vision OCR을 활용하여 약 봉투에서 약품명을 추출하고 질병 정보를 자동 등록합니다.

### 처리 과정

```text
약 봉투 촬영
    ↓
OCR 분석
    ↓
약품명 추출
    ↓
약품 DB 조회
    ↓
질병 자동 등록
```

### 적용 기술

- Google Cloud Vision API
- 문자열 전처리 및 패턴 매칭
- 약품 DB 기반 질병 분류

</details>

<details>
<summary><b>🍱 음식 위험도 자동 분석</b></summary>

<br>

사용자의 질병 및 건강 정보를 기반으로 메뉴 위험도를 계산합니다.

### 지원 질환

- 고혈압 (Hypertension)
- 당뇨 (Diabetes)
- 통풍 (Gout)

### 위험도 분류

| 등급 | 설명 |
|------|------|
| 🟢 SAFE | 안심하고 섭취 가능 |
| 🟡 MODERATE | 적당량 섭취 권장 |
| 🔴 HIGH_RISK | 섭취 비권장 |

### 주요 평가 요소

- 나트륨
- 탄수화물
- 당류
- 지방
- 포화지방
- 퓨린

</details>

<details>
<summary><b>🎯 개인화 메뉴 추천</b></summary>

<br>

위험 메뉴를 제외한 후 사용자 맞춤 추천 점수를 계산합니다.

### 추천 기준

- 안전 점수
- 전체 인기도
- 개인 선호도
- 동일 질환 사용자 선호도

### 추천 결과

- Top 10 메뉴 추천
- 질환별 맞춤 추천
- 위험 메뉴 자동 제외

</details>

---

## 🛠 Tech Stack

### Backend

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/JPA-59666C?style=for-the-badge)

### Frontend

![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

### Database

![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
### AI / OCR

![Google Cloud](https://img.shields.io/badge/Google_Vision_API-4285F4?style=for-the-badge&logo=googlecloud&logoColor=white)

---

## 🏗 Architecture
<img width="706" height="309" alt="스크린샷 2026-05-28 오후 11 21 47" src="https://github.com/user-attachments/assets/5d5b845e-9029-428c-992c-7fc3897a9ce7" />
---

## 📷 주요 화면
<img width="1476" height="801" alt="스크린샷 2026-05-28 오후 11 23 58" src="https://github.com/user-attachments/assets/ceb606ad-88f8-48a1-893e-e6b99d3e7944" />
<img width="1451" height="796" alt="스크린샷 2026-05-28 오후 11 24 11" src="https://github.com/user-attachments/assets/550fa15d-17c8-4fcb-b1c6-f42102da30e2" />
<img width="1471" height="806" alt="스크린샷 2026-05-28 오후 11 24 38" src="https://github.com/user-attachments/assets/d9cb5915-4f04-40c6-8082-4d344db63540" />

</div>

---

## 🏆 성과
<div align="center">
- 🥇 2024 K-Digital Training 해커톤 고용노동부 장관상(우수상)
- OCR 기반 질병 자동 등록 서비스 구현
- 개인 맞춤형 음식 추천 시스템 구축
  
🏆 **2024 제6회 K-Digital Training 해커톤**
### 고용노동부 장관상(우수상)
<img width="687" height="465" alt="스크린샷 2026-05-28 오후 11 18 10" src="https://github.com/user-attachments/assets/c64708ea-647c-412d-a09c-cd41617f3dfd" />



---
