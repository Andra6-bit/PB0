package database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import models.Freelancer;
import models.Project;
import models.ActivityLog;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> freelancers;
    private MongoCollection<Document> projects;
    private MongoCollection<Document> logs;

    public DatabaseManager() {
        try {
            // ubah jika mau pakai Atlas SRV
            String uri = "mongodb+srv://oopuser:oop12345@cluster0.0aguo0b.mongodb.net/?appName=Cluster0";
            // contoh Atlas SRV: "mongodb+srv://user:pass@cluster0.xxxxx.mongodb.net/?retryWrites=true&w=majority"
            client = MongoClients.create(uri);
            db = client.getDatabase("oop");

            freelancers = db.getCollection("freelancers");
            projects = db.getCollection("projects");
            logs = db.getCollection("activity_logs");

            System.out.println("Local MongoDB Connected!");
        } catch (Exception e) {
            System.err.println("MongoDB connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* --------- utilities to avoid NPE and numeric type issues --------- */
    private String s(String v) { return v == null ? "" : v; }

    private double getNumberAsDouble(Document doc, String key) {
        Object o = doc.get(key);
        if (o == null) return 0.0;
        if (o instanceof Double) return (Double) o;
        if (o instanceof Integer) return ((Integer) o).doubleValue();
        if (o instanceof Long) return ((Long) o).doubleValue();
        try { return Double.parseDouble(o.toString()); } catch (Exception ex) { return 0.0; }
    }

    /* ---------------- FREELANCER CRUD ---------------- */

    public List<Freelancer> getAllFreelancers() {
        List<Freelancer> out = new ArrayList<>();
        for (Document doc : freelancers.find()) {
            Freelancer f = new Freelancer(
                    s(doc.getString("name")),
                    s(doc.getString("skill")),
                    s(doc.getString("email")),
                    getNumberAsDouble(doc, "ratePerHour"),
                    s(doc.getString("experience"))
            );
            f.setId(doc.getObjectId("_id"));
            List<ObjectId> taken = doc.getList("takenProjects", ObjectId.class);
            f.setTakenProjects(taken != null ? taken : new ArrayList<>());
            out.add(f);
        }
        return out;
    }

    public Freelancer getFreelancerByName(String name) {
        Document doc = freelancers.find(Filters.eq("name", name)).first();
        if (doc == null) return null;
        Freelancer f = new Freelancer(
                s(doc.getString("name")),
                s(doc.getString("skill")),
                s(doc.getString("email")),
                getNumberAsDouble(doc, "ratePerHour"),
                s(doc.getString("experience"))
        );
        f.setId(doc.getObjectId("_id"));
        List<ObjectId> taken = doc.getList("takenProjects", ObjectId.class);
        f.setTakenProjects(taken != null ? taken : new ArrayList<>());
        return f;
    }

    public void insertFreelancer(Freelancer f) {
        Document doc = new Document("name", f.getName())
                .append("skill", f.getSkill())
                .append("email", f.getEmail())
                .append("ratePerHour", f.getRatePerHour())
                .append("experience", f.getExperience())
                .append("takenProjects", new ArrayList<>());
        freelancers.insertOne(doc);
        log("SYSTEM", "Insert Freelancer", f.getName());
    }

    public void updateFreelancer(Freelancer f) {
        freelancers.updateOne(
                Filters.eq("_id", f.getId()),
                Updates.combine(
                        Updates.set("name", f.getName()),
                        Updates.set("skill", f.getSkill()),
                        Updates.set("email", f.getEmail()),
                        Updates.set("ratePerHour", f.getRatePerHour()),
                        Updates.set("experience", f.getExperience())
                )
        );
        log("SYSTEM", "Update Freelancer", f.getName());
    }

    public void deleteFreelancer(ObjectId id) {
        freelancers.deleteOne(Filters.eq("_id", id));
        log("SYSTEM", "Delete Freelancer", id.toHexString());
    }

    /* ---------------- PROJECT CRUD ---------------- */

    public void insertProject(Project p) {
        Document doc = new Document("title", p.getTitle())
                .append("companyName", p.getCompanyName())
                .append("description", p.getDescription())
                .append("requiredSkills", p.getRequiredSkills())
                .append("budget", p.getBudget())
                .append("assignedTo", null)
                .append("progress", "Belum Dikerjakan");

        projects.insertOne(doc);

        log("HRD", "Buat Proyek", "Membuat proyek: " + p.getTitle());
    }

    public List<Project> getAllProjects() {
        List<Project> out = new ArrayList<>();
        for (Document doc : projects.find()) {
            Project p = new Project(
                    s(doc.getString("title")),
                    s(doc.getString("companyName")),
                    s(doc.getString("description")),
                    s(doc.getString("requiredSkills")),
                    getNumberAsDouble(doc, "budget")
            );
            p.setId(doc.getObjectId("_id"));
            p.setProgress(s(doc.getString("progress")));
            out.add(p);
        }
        return out;
    }

    public List<Project> getAvailableProjects() {
        List<Project> out = new ArrayList<>();
        for (Document doc : projects.find(Filters.eq("assignedTo", null))) {
            Project p = new Project(
                    s(doc.getString("title")),
                    s(doc.getString("companyName")),
                    s(doc.getString("description")),
                    s(doc.getString("requiredSkills")),
                    getNumberAsDouble(doc, "budget")
            );
            p.setId(doc.getObjectId("_id"));
            p.setProgress(s(doc.getString("progress")));
            out.add(p);
        }
        return out;
    }

    public List<Project> getProjectsByFreelancer(ObjectId freelancerId) {
        List<Project> out = new ArrayList<>();
        for (Document doc : projects.find(Filters.eq("assignedTo", freelancerId))) {
            Project p = new Project(
                    s(doc.getString("title")),
                    s(doc.getString("companyName")),
                    s(doc.getString("description")),
                    s(doc.getString("requiredSkills")),
                    getNumberAsDouble(doc, "budget")
            );
            p.setId(doc.getObjectId("_id"));
            p.setProgress(s(doc.getString("progress")));
            out.add(p);
        }
        return out;
    }

    public Project findProjectByTitle(String title) {
        Document doc = projects.find(Filters.eq("title", title)).first();
        if (doc == null) return null;

        Project p = new Project(
                doc.getString("title"),
                doc.getString("companyName"),
                doc.getString("description"),
                doc.getString("requiredSkills"),
                doc.getDouble("budget")
        );
        p.setId(doc.getObjectId("_id"));
        p.setProgress(doc.getString("progress"));

        return p;
    }


    public void deleteProject(ObjectId id) {
        projects.deleteOne(Filters.eq("_id", id));
        log("SYSTEM", "Delete Project", id.toHexString());
    }

    /* ---------------- Assign & Progress ---------------- */

    public boolean assignProjectToFreelancer(ObjectId projectId, ObjectId freelancerId) {
        Document doc = projects.find(Filters.eq("_id", projectId)).first();

        if (doc == null) return false;
        if (doc.get("assignedTo") != null) return false;  // sudah ada yang ambil

        projects.updateOne(
            Filters.eq("_id", projectId),
            Updates.set("assignedTo", freelancerId)
        );

        log(freelancerId.toString(), "Ambil Project", "Freelancer mengambil project ID: " + projectId);

        return true;
    }


    public void updateProjectProgress(ObjectId projectId, String progress) {
        projects.updateOne(
            Filters.eq("_id", projectId),
            Updates.set("progress", progress)
        );

        log("SYSTEM", "Update Progress", "Progress project " + projectId + " => " + progress);
    }


    /* ---------------- LOGGING ---------------- */

    public void log(String user, String action, String detail) {
        logs.insertOne(
                new Document("timestamp", java.time.LocalDateTime.now().toString())
                        .append("user", user)
                        .append("action", action)
                        .append("detail", detail)
        );
    }

    public java.util.List<ActivityLog> getActivityLogs() {
        List<ActivityLog> out = new ArrayList<>();
        for (Document doc : logs.find()) {
            out.add(new ActivityLog(
                    s(doc.getString("timestamp")),
                    s(doc.getString("user")),
                    s(doc.getString("action")),
                    s(doc.getString("detail"))
            ));
        }
        return out;
    }
}
