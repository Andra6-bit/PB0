package models;

import org.bson.types.ObjectId;

public class Project {
    private ObjectId id;
    private String title;
    private String companyName;
    private String description;
    private String requiredSkills;
    private double budget;
    private String progress;
    private ObjectId assignedTo;

    public Project(String title, String companyName, String description, String requiredSkills, double budget) {
        this.id = new ObjectId();
        this.title = title;
        this.companyName = companyName;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.budget = budget;
        this.progress = "Belum Dikerjakan";
        this.assignedTo = null;
    }

    public Project() {
        this.id = new ObjectId();
        this.progress = "Belum Dikerjakan";
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public String getProgress() { return progress; }
    public void setProgress(String progress) { this.progress = progress; }

    public ObjectId getAssignedTo() { return assignedTo; }
    public void setAssignedTo(ObjectId assignedTo) { this.assignedTo = assignedTo; }

    @Override
    public String toString() {
        return title + " | " + requiredSkills + " | Rp " + budget + " | " + progress;
    }
}
