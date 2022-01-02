package model;

public class Task {

    private Boolean status;
    private String category;
    private String taskDescription;

    public Task() {
    }

    public Task(Boolean status, String category, String taskDescription) {
        this.status = status;
        this.category = category;
        this.taskDescription = taskDescription;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
