package com.cookandroid.talsuke.Model;

public class StatDay {
    private String day;
    private String weight;
    private String fee;

    public StatDay(String day, String weight, String fee){
        this.day = day;
        this.weight = weight;
        this.fee = fee;
    }

    public String getWeight() {return weight;}
    public String getDay() {return day;}
    public String getFee() {return fee;}
    public void setWeight(String weight) {this.weight = weight;}
    public void setDay(String day) {this.day = day;}
    public void setFee(String fee) {this.fee = fee;}
}
