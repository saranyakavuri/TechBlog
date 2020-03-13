package com.tech.blog.profile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties("stillWorking")
public class WorkExperience {

    private String role;
    private String companyName;
    private String location;
    private LocalDate yearStarted;
    private LocalDate yearEnded;
    private String responsibilities;
    @JsonProperty("isStillWorking")
    private boolean isStillWorking;

    public WorkExperience(){

    }

    public WorkExperience(String role, String companyName, String location, LocalDate yearStarted, LocalDate yearEnded, String responsibilities, boolean isStillWorking) {
        this.role = role;
        this.companyName = companyName;
        this.location = location;
        this.yearStarted = yearStarted;
        this.yearEnded = yearEnded;
        this.responsibilities = responsibilities;
        this.isStillWorking = isStillWorking;
    }


    public String getRole() {
        return role;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getYearStarted() {
        return yearStarted;
    }

    public LocalDate getYearEnded() {
        return yearEnded;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public boolean isStillWorking() {
        return isStillWorking;
    }

    @Override
    public String toString() {
        return "WorkExperience{" +
                "role='" + role + '\'' +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", yearStarted=" + yearStarted +
                ", yearEnded=" + yearEnded +
                ", responsibilities='" + responsibilities + '\'' +
                ", isStillWorking=" + isStillWorking +
                '}';
    }
}
