package com.romain.cellarv1.modele;

import java.util.Date;

public class WineBottle {

    // Propriétés
    private Date dateMesure;
    private String country;
    private String region;
    private Integer wineColor;
    private String domain;
    private String appellation;
    private Integer year;
    private Integer number;
    private Integer estimate;
    private String image;

    // Constructeur
    public WineBottle(Date dateMesure, String country, String region, Integer wineColor, String domain, String appellation, Integer year, Integer number, Integer estimate, String image) {
        this.dateMesure = dateMesure;
        this.country = country;
        this.region = region;
        this.wineColor = wineColor;
        this.domain = domain;
        this.appellation = appellation;
        this.year = year;
        this.number = number;
        this.estimate = estimate;
        this.image = image;
    }

    // Getters et setters
    public Date getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getWineColor() {
        return wineColor;
    }

    public void setWineColor(Integer wineColor) {
        this.wineColor = wineColor;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }


    @Override
    public String toString() {
        return "WineBottle{" +
                "dateMesure=" + dateMesure +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", wineColor=" + wineColor +
                ", domain='" + domain + '\'' +
                ", appellation='" + appellation + '\'' +
                ", year=" + year +
                ", number=" + number +
                ", estimate=" + estimate +
                ", image='" + image + '\'' +
                '}';
    }
}
