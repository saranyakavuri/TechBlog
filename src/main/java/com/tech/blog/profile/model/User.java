package com.tech.blog.profile.model;

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

    public User() {

    }

    private User(UserBuilder userBuilder) {
        this.userId =  userBuilder.userId;
        this.summary = userBuilder.summary;
        this.contact = userBuilder.contact;
        this.workExperienceList = userBuilder.workExperienceList;
        this.skillList = userBuilder.skillList;
        this.certificationList = userBuilder.certificationList;
        this.educationList = userBuilder.educationList;
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
        return workExperienceList;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public List<Certification> getCertificationList() {
        return certificationList;
    }

    public static class UserBuilder{
        private String userId;
        private String summary;
        private Contact contact;
        private List<WorkExperience> workExperienceList;
        private List<Skill> skillList;
        private List<Certification> certificationList;
        private List<Education> educationList;

        public UserBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public UserBuilder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public UserBuilder setWorkExperienceList(List<WorkExperience> workExperienceList) {
            this.workExperienceList = workExperienceList;
            return this;
        }

        public UserBuilder setSkillList(List<Skill> skillList) {
            this.skillList = skillList;
            return this;
        }

        public UserBuilder setCertificationList(List<Certification> certificationList) {
            this.certificationList = certificationList;
            return this;
        }

        public UserBuilder setEducationList(List<Education> educationList) {
            this.educationList = educationList;
            return this;
        }

        public User build() {
            if (this.certificationList == null) {
                this.certificationList = new ArrayList<>();
            }
            if (this.workExperienceList == null) {
                this.workExperienceList = new ArrayList<>();
            }

            if (this.skillList == null) {
                this.skillList = new ArrayList<>();
            }

            if (this.educationList == null) {
                this.educationList = new ArrayList<>();
            }
            return new User(this);
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
