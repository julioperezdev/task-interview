package service;

import java.util.List;

public interface RemindersService {

    void addTask(String category, String taskDescription);

    List<String> tasks();

    List<String> tasks(String category);

    List<String> pendingTasks(String category);

    void done(String category, String taskDescription);

    void done(String category);

}
