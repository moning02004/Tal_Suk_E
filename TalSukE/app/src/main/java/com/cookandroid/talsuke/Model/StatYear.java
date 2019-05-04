package com.cookandroid.talsuke.Model;

import java.util.ArrayList;

public class StatYear {
    private String year;
    private ArrayList<StatMonth> monthList;

    public StatYear(String year){
        this.year = year;
        this.monthList = new ArrayList<StatMonth>();
    }

    public String getYear() {return year;}
    public ArrayList<StatMonth> getMonthList() {return monthList;}
    public void setYear(String year) {this.year = year;}
    public void setMonthList(ArrayList<StatMonth> monthList) {this.monthList = monthList;}

    public void addMonth(StatMonth month) {this.monthList.add(month);}
}
