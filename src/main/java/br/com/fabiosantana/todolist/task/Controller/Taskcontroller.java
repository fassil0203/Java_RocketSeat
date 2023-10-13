package br.com.fabiosantana.todolist.task.Controller;

import br.com.fabiosantana.todolist.task.Model.TaskModel;
import br.com.fabiosantana.todolist.task.Repository.ItaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class Taskcontroller {

    @Autowired
    private ItaskRepository  taskRepository;


    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel){
        var task = this.taskRepository.save(taskModel);
        return task;

    }
}
