package models;

public class ActivityLog {
    private String timestamp;
    private String user;
    private String action;
    private String detail;

    public ActivityLog(String timestamp, String user, String action, String detail) {
        this.timestamp = timestamp;
        this.user = user;
        this.action = action;
        this.detail = detail;
    }

    public String getTimestamp() { return timestamp; }
    public String getUser() { return user; }
    public String getAction() { return action; }
    public String getDetail() { return detail; }
}
