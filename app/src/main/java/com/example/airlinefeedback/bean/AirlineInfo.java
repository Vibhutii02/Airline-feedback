package com.example.airlinefeedback.bean;

public class AirlineInfo {

    private String Name;
    private String PNR;

    public AirlineInfo(String name, String PNR) {
        Name = name;
        this.PNR = PNR;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }
}
