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

    // Getters
    public Date getDate() {
        return dateMesure;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public Integer getWineColor() {
        return wineColor;
    }

    public String getDomain() {
        return domain;
    }

    public String getAppellation() {
        return appellation;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "WineBottle{" +
                "date=" + dateMesure +
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
