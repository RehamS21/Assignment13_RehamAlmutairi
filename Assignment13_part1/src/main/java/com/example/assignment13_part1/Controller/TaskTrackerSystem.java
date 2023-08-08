package com.example.assignment13_part1.Controller;

import com.example.assignment13_part1.ApiResponse.ApiResponse;
import com.example.assignment13_part1.Model.Task_tracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskTrackerSystem {
    ArrayList<Task_tracker> taskTrackers = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse postTasksTracker(@RequestBody Task_tracker taskTracker){
        if ((taskTracker.getId() != null && taskTracker.getTitle() != null) && ( taskTracker.getDescription() != null && taskTracker.getStatus() !=null ) ) {
            taskTrackers.add(taskTracker);
            return new ApiResponse("Add task successfully");
        }
        else
            return new ApiResponse("You have attributes with null value, try again");
    }

    @GetMapping("/get")
    public ArrayList<Task_tracker> getTaskTrackers(){
        return taskTrackers;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTaskTrackers(@PathVariable int index, @RequestBody Task_tracker taskTracker){
        if(index >= 0) {
            taskTrackers.set(index, taskTracker);
            return new ApiResponse("update tasks successfully");
        }else
            return new ApiResponse("invalid index");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse removeTaskTracker(@PathVariable int index){
        if(index >=0) {
            taskTrackers.remove(index);
            return new ApiResponse("delete tasks successfully");
        }else
            return new ApiResponse("invalid index");
    }

    @PutMapping("/change/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        if(taskTrackers.get(index).getStatus().equalsIgnoreCase("not done")) {
            taskTrackers.get(index).setStatus("done");
            return new ApiResponse("changed tasks successfully, the tasks after update { task title:" + getTaskTrackers().get(index).getTitle() + ", task status: " +
                    getTaskTrackers().get(index).getStatus() + " }");
        }else
            return new ApiResponse("the task already done");
    }

    @GetMapping("/search/{title}")
    public ApiResponse search(@PathVariable String title){
        for (int i = 0; i < taskTrackers.size(); i++) {
            if(taskTrackers.get(i).getTitle().equalsIgnoreCase(title)){
                return new ApiResponse(title +" founded in tasks tracker system");
            }
        }
        return new ApiResponse("Sorry, the '"+title +"' Not founded in tasks tracker system");
    }
}
