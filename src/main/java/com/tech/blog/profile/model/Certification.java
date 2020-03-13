package com.tech.blog.profile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties("neverExpires")
public class Certification {

    private String name;
    private LocalDate yearIssued;
    private LocalDate yearExpired;
    private String link;
    @JsonProperty("isNeverExpires")
    private boolean isNeverExpires;

    public Certification() {

    }

    public Certification(String name, LocalDate yearIssued, LocalDate yearExpired, String link, boolean isNeverExpires) {
        this.name = name;
        this.yearIssued = yearIssued;
        this.yearExpired = yearExpired;
        this.link = link;
        this.isNeverExpires = isNeverExpires;
    }

    public String getName() {
        return name;
    }

    public LocalDate getYearIssued() {
        return yearIssued;
    }

    public LocalDate getYearExpired() {
        return yearExpired;
    }

    public String getLink() {
        return link;
    }

    public boolean isNeverExpires() {
        return isNeverExpires;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "name='" + name + '\'' +
                ", yearIssued=" + yearIssued +
                ", yearExpired=" + yearExpired +
                ", link='" + link + '\'' +
                ", isNeverExpires=" + isNeverExpires +
                '}';
    }
}
