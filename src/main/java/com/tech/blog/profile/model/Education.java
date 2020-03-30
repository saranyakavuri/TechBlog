package com.tech.blog.profile.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 *   public schoolName: string;
 *     public yearStarted: Date;
 *     public yearEnded: Date;
 *     public typeOfDegree: string;
 *     public courseName: string;
 *     public gpa : number;
 *     public stillStudying: boolean;
 */
@JsonIgnoreProperties("stillStudying")
public class Education {

    private String schoolName;
    private LocalDate yearStarted;
    private LocalDate yearEnded;
    private String typeOfDegree;
    private String courseName;
    private int gpa;
    @JsonProperty("isStillStudying")
    private boolean isStillStudying;


    public Education(){

    }

    public Education(String schoolName, LocalDate yearStarted, LocalDate yearEnded, String typeOfDegree, String courseName, int gpa, boolean isStillStudying) {
        this.schoolName = schoolName;
        this.yearStarted = yearStarted;
        this.yearEnded = yearEnded;
        this.typeOfDegree = typeOfDegree;
        this.courseName = courseName;
        this.gpa = gpa;
        this.isStillStudying = isStillStudying;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public LocalDate getYearStarted() {
        return yearStarted;
    }

    public LocalDate getYearEnded() {
        return yearEnded;
    }

    public String getTypeOfDegree() {
        return typeOfDegree;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getGpa() {
        return gpa;
    }

    public boolean isStillStudying() {
        return isStillStudying;
    }
}
