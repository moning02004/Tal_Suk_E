package com.cookandroid.talsuke.Model;

import java.util.ArrayList;

public class StatMonth {
    private String month;
    private String weight;
    private String fee;
    private ArrayList<StatDay> dayList;

    public StatMonth(String month, String weight, String fee){
        this.month = month;
        this.weight = weight;
        this.fee = fee;
        this.dayList = new ArrayList<StatDay>();
    }

    public String getWeight() {return weight;}
    public String getMonth() {return month;}
    public String getFee() {return fee;}
    public void setWeight(String weight) {this.weight = weight;}
    public void setMonth(String month) {this.month = month;}
    public void setFee(String fee) {this.fee = fee;}

    public ArrayList<StatDay> getDayList() {return dayList;}
    public void setDayList(ArrayList<StatDay> dayList) {this.dayList = dayList;}
    public void addDay(StatDay day){this.dayList.add(day);}
}
