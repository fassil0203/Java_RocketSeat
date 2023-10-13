package br.com.fabiosantana.todolist.user.Controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.fabiosantana.todolist.IUserRepository.IUserRepository;
import br.com.fabiosantana.todolist.UserModel.java.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Modificador
 * public
 * private
 * protected
  * */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    /**
     * *String   Text
     * *Integer  int numeros inteiros
     * Double double numeros 0.000
     * Float float numeros 0.000
     * char A C
     * Date data
     * void  nao tem retorno do metodo, somente uma logica
     */

        /*
        * Body Corpo da Requisicao
         */

        @PostMapping("/")
        public ResponseEntity create(@RequestBody UserModel userModel){
           var user = this.userRepository.findByUsername(userModel.getUsername());
            if(user != null) {

                //Mensagem de Erro
                // Status Code
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario Já Existe");
            }
           var passwordHashred = BCrypt.withDefaults()
                .hashToString(12,userModel.getPassword().toCharArray());  //transforma o getpassword em array

            userModel.setPassword(passwordHashred);
           var userCreated = this.userRepository.save(userModel);

           return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        }
    }
