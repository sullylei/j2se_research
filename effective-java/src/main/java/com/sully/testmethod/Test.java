package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年10月24日
 * 类功能描述：
 */
public class Test {
    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new NutritionFacts.Builder(200,80)
                .calories(100).fat(20).carbohydrate(203).sodium(35).build();
    }
}
