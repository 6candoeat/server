package com._candoit.drfood.validator;

import com._candoit.drfood.domain.Nutrition;
import com._candoit.drfood.domain.PurineRiskConst;
import com._candoit.drfood.enums.RiskLevel;

import java.math.BigDecimal;

public class RiskLevelValidator {
    public static RiskLevel validateGout(BigDecimal totalPurineAmount) {
        if (totalPurineAmount.compareTo(PurineRiskConst.PURINE_SAFE) < 0) {
            return RiskLevel.SAFE;
        }

        if (totalPurineAmount.compareTo(PurineRiskConst.PURINE_SAFE) >= 0 && totalPurineAmount.compareTo(PurineRiskConst.PURINE_MODERATE) < 0) {
            return RiskLevel.MODERATE;
        }
        return RiskLevel.HIGH_RISK;
    }

    public static RiskLevel validateStrictGout(BigDecimal totalPurineAmount) {
        if (totalPurineAmount.compareTo(PurineRiskConst.STRICT_PURINE_SAFE) < 0) {
            return RiskLevel.SAFE;
        }

        if (totalPurineAmount.compareTo(PurineRiskConst.STRICT_PURINE_SAFE) >= 0 && totalPurineAmount.compareTo(PurineRiskConst.STRICT_PURINE_MODERATE) < 0) {
            return RiskLevel.MODERATE;
        }
        return RiskLevel.HIGH_RISK;
    }

    public static RiskLevel validateDiabetes(Nutrition nutrition, BigDecimal dailyEnergyPerMeal) {
        BigDecimal carbohydrates = nutrition.getCarbohydrates();
        BigDecimal protein = nutrition.getProtein();
        BigDecimal fat = nutrition.getFat();
        BigDecimal saturatedFat = nutrition.getSaturatedFat();
        BigDecimal transFat = nutrition.getTransFat();
        BigDecimal sodium = nutrition.getSodium();
        BigDecimal sugar = nutrition.getSugar();

        if (carbohydrates.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.6"))) < 0 &&
                protein.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.2"))) < 0 &&
                fat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.2"))) < 0 &&
                saturatedFat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.07"))) < 0 &&
                transFat.compareTo(new BigDecimal("0.66")) < 0 &&
                sodium.compareTo(new BigDecimal("767")) < 0 &&
                sugar.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.08"))) < 0) {
            return RiskLevel.SAFE; // 안전
        }

        if (carbohydrates.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.63"))) < 0 &&
                protein.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.21"))) < 0 &&
                fat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.25"))) < 0 &&
                saturatedFat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.1"))) < 0 &&
                transFat.compareTo(new BigDecimal("0.7")) < 0 &&
                sodium.compareTo(new BigDecimal("900")) < 0 &&
                sugar.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.1"))) < 0) {
            return RiskLevel.SAFE; // 안전
        }
        return RiskLevel.HIGH_RISK;
    }

    public static RiskLevel validateStrictDiabetes(Nutrition nutrition, BigDecimal dailyEnergyPerMeal) {
        BigDecimal carbohydrates = nutrition.getCarbohydrates();
        BigDecimal protein = nutrition.getProtein();
        BigDecimal fat = nutrition.getFat();
        BigDecimal saturatedFat = nutrition.getSaturatedFat();
        BigDecimal transFat = nutrition.getTransFat();
        BigDecimal sodium = nutrition.getSodium();
        BigDecimal sugar = nutrition.getSugar();

        if (carbohydrates.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.55"))) < 0 &&
                protein.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.18"))) < 0 &&
                fat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.18"))) < 0 &&
                saturatedFat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.06"))) < 0 &&
                transFat.compareTo(new BigDecimal("0.60")) < 0 &&
                sodium.compareTo(new BigDecimal("700")) < 0 &&
                sugar.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.07"))) < 0) {
            return RiskLevel.SAFE; // 안전
        }

        if (carbohydrates.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.60"))) < 0 &&
                protein.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.20"))) < 0 &&
                fat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.22"))) < 0 &&
                saturatedFat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.08"))) < 0 &&
                transFat.compareTo(new BigDecimal("0.66")) < 0 &&
                sodium.compareTo(new BigDecimal("800")) < 0 &&
                sugar.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.09"))) < 0) {
            return RiskLevel.SAFE; // 안전
        }
        return RiskLevel.HIGH_RISK;
    }

    public static RiskLevel validateHyperTension(Nutrition nutrition, BigDecimal dailyEnergyPerMeal) {
        BigDecimal fat = nutrition.getFat();
        BigDecimal saturatedFat = nutrition.getSaturatedFat();
        BigDecimal transFat = nutrition.getTransFat();
        BigDecimal sodium = nutrition.getSodium();

        if (sodium.compareTo(new BigDecimal("767")) < 0 &&
                fat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.2"))) < 0 &&
                saturatedFat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.07"))) < 0 &&
                transFat.compareTo(new BigDecimal("0.66")) < 0) {
            return RiskLevel.SAFE; // 안전
        }

        if (sodium.compareTo(new BigDecimal("900")) < 0 &&
                fat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.25"))) < 0 &&
                saturatedFat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.1"))) < 0 &&
                transFat.compareTo(new BigDecimal("0.7")) < 0) {
            return RiskLevel.MODERATE; // 보통
        }

        return RiskLevel.HIGH_RISK;
    }


    public static RiskLevel validateStrictHyperTension(Nutrition nutrition, BigDecimal dailyEnergyPerMeal) {
        BigDecimal fat = nutrition.getFat();
        BigDecimal saturatedFat = nutrition.getSaturatedFat();
        BigDecimal transFat = nutrition.getTransFat();
        BigDecimal sodium = nutrition.getSodium();

        if (sodium.compareTo(new BigDecimal("700")) < 0 &&
                fat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.18"))) < 0 &&
                saturatedFat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.06"))) < 0 &&
                transFat.compareTo(new BigDecimal("0.60")) < 0) {
            return RiskLevel.SAFE; // 안전
        }

        if (sodium.compareTo(new BigDecimal("800")) < 0 &&
                fat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.22"))) < 0 &&
                saturatedFat.compareTo(dailyEnergyPerMeal.multiply(new BigDecimal("0.08"))) < 0 &&
                transFat.compareTo(new BigDecimal("0.66")) < 0) {
            return RiskLevel.MODERATE; // 보통
        }

        return RiskLevel.HIGH_RISK;
    }
}
