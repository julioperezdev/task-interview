package service;

import exception.CategoryNotFoundException;
import model.Task;

import java.util.*;
import java.util.stream.Collectors;

public class Reminders implements RemindersService{

    //TaskRepository taskRepository = new TaskRepository();

    private final List<Task> taskList = new ArrayList<>();

    @Override
    public void addTask(String category, String taskDescription) {
        Task newTask = new Task(false, category, taskDescription);
        taskList.add(newTask);
    }

    @Override
    public List<String> tasks() {
//        return  taskList.stream()
//                .map(Task::getTask)
//                .toList();
        List<String> allTasks = new ArrayList<>();
        List<String> allCategories = taskList.stream()
                .map(Task::getCategory)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        for (String category : allCategories) {
            gettingTaskByCategory(allTasks, category);
        }
        return allTasks;
    }

    private void gettingTaskByCategory(List<String> allTasks, String category) {
        List<String> eachTaskByCategorySorted = tasks(category);
        allTasks.addAll(eachTaskByCategorySorted);
    }

    @Override
    public List<String> tasks(String category) {
        List<String> tasksFiltered = taskList.stream()
                .filter(task -> Objects.equals(task.getCategory(), category))
                .map(Task::getTaskDescription)
                .sorted()
                .collect(Collectors.toList());

        if(tasksFiltered.isEmpty()){
            throw new CategoryNotFoundException();
        }
        //Collections.sort(tasksFiltered);
        return tasksFiltered;
//
//        List<String> lista = new ArrayList<>();
//        for (Task particularTask : taskList){
//            if(Objects.equals(particularTask.getCategory(), category)){
//                lista.add(particularTask.getTaskDescription());
//            }
//        }
//        if(tasksFiltered.isEmpty()){
//            throw new CategoryNotFoundException();
//        }
//        Collections.sort(lista);
//        return lista;
    }

    @Override
    public List<String> pendingTasks(String category) {
        return taskList.stream()
                .filter(task -> task.getStatus().equals(false) && Objects.equals(task.getCategory(), category))
                .map(Task::getTaskDescription)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void done(String category, String taskDescription) {
        List<Task> tasksFiltered = taskList.stream()
                .filter(task -> task.getStatus().equals(false) && Objects.equals(task.getCategory(), category) && Objects.equals(task.getTaskDescription(), taskDescription))
                .collect(Collectors.toList());
        if(tasksFiltered.isEmpty()){
            throw new CategoryNotFoundException();
        }
        tasksFiltered.forEach(task -> task.setStatus(true));
    }

    @Override
    public void done(String category) {
        List<Task> tasksFiltered = taskList.stream()
                .filter(task -> task.getStatus().equals(false) && Objects.equals(task.getCategory(), category))
                .collect(Collectors.toList());
        if(tasksFiltered.isEmpty()){
            throw new CategoryNotFoundException();
        }
        tasksFiltered.forEach(task -> task.setStatus(true));
    }
}
