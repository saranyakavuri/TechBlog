package com.tech.blog.model;

import java.util.Optional;

public class Skill {

    private String name;
    private Optional<Integer> experience;

    public Skill() {

    }

    public Skill(String name, Optional<Integer> experience) {
        this.name = name;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public Optional<Integer> getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
}
