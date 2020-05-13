package com.tech.blog.model;

import java.util.ArrayList;
import java.util.List;

/**
 * empleoyee
 *
 * firstname
 * lastname
 * address -> state city street aptNo
 * email
 * phoneNumber
 * payroll
 */
public class User {
    private String userId;
    private String summary;
    private Contact contact;
    private List<WorkExperience> workExperienceList;
    private List<Skill> skillList;
    private List<Certification> certificationList;
    private List<Education> educationList;

    public User() {}

    public static User createNewUser(String id) {
        return new User(id, "",  new Contact("", "", "", "", "", ""),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public User(String userId, String summary, Contact contact, List<WorkExperience> workExperienceList,
                List<Skill> skillList, List<Certification> certificationList, List<Education> educationList) {
        this.userId = userId;
        this.summary = summary;
        this.contact = contact;
        this.workExperienceList = workExperienceList;
        this.skillList = skillList;
        this.certificationList = certificationList;
        this.educationList = educationList;
    }

    public String getUserId() {
        return userId;
    }

    public String getSummary() {
        return summary;
    }

    public Contact getContact() {
        return contact;
    }

    public List<WorkExperience> getWorkExperienceList() {
        if (workExperienceList == null) {
            return new ArrayList<>();
        }
        return workExperienceList;
    }

    public List<Skill> getSkillList() {
        if (skillList == null) {
            return new ArrayList<>();
        }
        return skillList;
    }

    public List<Certification> getCertificationList() {
        if (certificationList == null) {
            return new ArrayList<>();
        }
        return certificationList;
    }

    public List<Education> getEducationList() {
        if (educationList == null) {
            return new ArrayList<>();
        } else {
            return educationList;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", summary='" + summary + '\'' +
                ", contact=" + contact +
                ", workExperienceList=" + workExperienceList +
                ", skillList=" + skillList +
                ", certificationList=" + certificationList +
                ", educationList=" + educationList +
                '}';
    }
}
