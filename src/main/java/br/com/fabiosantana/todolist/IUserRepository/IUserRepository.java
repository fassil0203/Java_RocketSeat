package br.com.fabiosantana.todolist.IUserRepository;

import br.com.fabiosantana.todolist.UserModel.java.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository <UserModel, UUID> {
   UserModel findByUsername(String username);
}
