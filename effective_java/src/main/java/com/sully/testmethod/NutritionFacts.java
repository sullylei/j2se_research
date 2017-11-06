package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年10月23日
 * 类功能描述：Builder模式
 */
public class NutritionFacts {
    private final int servingSize;
    private final int serving;
    private final int calories;
    private final int fat;
    private final int carbohydrate;
    private final int sodium;
    public static class Builder{
        //required parameters
        private final int servingSize;
        private final int serving;

        //optional parameters - initialized
        private int calories = 0;
        private int fat = 0;
        private int carbohydrate = 0;
        private int sodium = 0;

        public Builder(int servingSize,int serving){
            this.servingSize = servingSize;
            this.serving = serving;
        }
        public Builder calories(int val){
            this.calories = val;
            return this ;
        }

        public Builder fat(int val){
            this.fat = val;
            return  this;
        }

        public Builder carbohydrate(int val){
            this.carbohydrate = val;
            return this;
        }

        public Builder sodium(int val){
            this.sodium = val;
            return this;
        }

        public NutritionFacts build(){
            return new NutritionFacts(this);
        }

    }
    private NutritionFacts(Builder builder){
        servingSize = builder.servingSize;
        serving = builder.serving;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
