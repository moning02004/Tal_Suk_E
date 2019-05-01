package com.cookandroid.talsuke;

class Items {
    private String date;
    private int weight;
    private int fee;

    Items(String date, int weight, int fee){
        this.date = date;
        this.weight = weight;
        this.fee = fee;

    }

    public int getWeight() {return weight;}
    public String getDate() {return date;}
    public int getFee() {return fee;}
    public void setWeight(int weight) {this.weight = weight;}
    public void setDate(String date) {this.date = date;}
    public void setFee(int fee) {this.fee = fee;}
}
