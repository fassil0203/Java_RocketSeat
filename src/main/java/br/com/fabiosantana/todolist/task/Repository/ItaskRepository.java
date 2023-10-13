package br.com.fabiosantana.todolist.task.Repository;

import br.com.fabiosantana.todolist.task.Model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItaskRepository extends JpaRepository<TaskModel, UUID> {
}
