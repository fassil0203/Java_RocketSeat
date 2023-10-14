package br.com.fabiosantana.todolist.task.Controller;

import br.com.fabiosantana.todolist.task.Model.TaskModel;
import br.com.fabiosantana.todolist.task.Repository.ItaskRepository;
import br.com.fabiosantana.todolist.utils.utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class Taskcontroller {

    @Autowired
    private ItaskRepository  taskRepository;


    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        //Validando a Data

        var currentDate = LocalDateTime.now();
        //10/11/2023 Current
        //10/10/2023 startAt

        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio /Data de Termino deve ser maior que a atual");

        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio deve ser menor que a data de Termino ");

        }


        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
        var task = this.taskRepository.findById(id).orElse(null);

        if (task == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tarefa nao encontrada!!!");


        }
        //Validando usuario Dono

        var idUser = request.getAttribute("idUser");

        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User sem permissao");

        }

        utils.copyNonNullProperties(taskModel, task);  //(source,target)
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);


    }

}
