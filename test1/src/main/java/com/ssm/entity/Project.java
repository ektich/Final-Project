package com.ssm.entity;

/**
 * @author 13979
 */
public class Project {
    private int projectId;
    private String title;
    private String supervisor;
    private String language;
    private String reference;
    private String degree;
    private String description;
    private String candidates;
    private String designated;
    private String level1;
    private String level2;
    private String level3;

    public int getId() { return projectId; }
    public void setId(int projectId) { this.projectId = projectId;}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSupervisor() { return supervisor; }
    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCandidates() { return candidates; }
    public void setCandidates(String candidates) { this.candidates = candidates; }

    public String getDesignated() { return designated; }
    public void setDesignated(String designated) { this.designated = designated; }

    public String getLevel1() { return level1; }
    public void setLevel1(String level1) { this.level1 = level1; }

    public String getLevel2() { return level2; }
    public void setLevel2(String level2) { this.level2 = level2; }

    public String getLevel3() { return level3; }
    public void setLevel3(String level3) { this.level3 = level3; }

    @Override
    public String toString() {
        return "project [id = " + projectId + ", title = " + title + ", supervisor = " + supervisor + ", " +
                "language = " + language + ", category = " + ", reference = " + reference + "," +
                "description = " + description +"]";
    }
}
