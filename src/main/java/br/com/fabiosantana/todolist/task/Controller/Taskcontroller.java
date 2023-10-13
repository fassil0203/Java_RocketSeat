package br.com.fabiosantana.todolist.task.Controller;

import br.com.fabiosantana.todolist.task.Model.TaskModel;
import br.com.fabiosantana.todolist.task.Repository.ItaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class Taskcontroller {

    @Autowired
    private ItaskRepository  taskRepository;


    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser =  request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        var task = this.taskRepository.save(taskModel);
        return task;

    }
}
