package br.com.rafael.accessviewerjava.controller;

import br.com.rafael.accessviewerjava.dto.CreateDepositDto;
import br.com.rafael.accessviewerjava.dto.UserDto;
import br.com.rafael.accessviewerjava.model.User;
import br.com.rafael.accessviewerjava.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    //conectar o serviço que a gente criou
    private final UserService userService;

    //constructor
    public UserController(UserService userService){
        this.userService = userService;
    }

    //setar rota de criação
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto userData){
        userData.setPassword(new BCryptPasswordEncoder().encode(userData.getPassword()));
        User createdUser = userService.createUser((userData));

        return  new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
        //tem que adicionar lá em cima @RequestMapping
    }

    @GetMapping
    public  ResponseEntity<List<User>> readUsers(){
        final List<User> allUsers = userService.readUsers();

        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable final String id) {

        final User user = userService.retrieveUser(Long.parseLong(id));

        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody final UserDto userData, @PathVariable final String id) {

        final User updatedUser = userService.updateUser(userData, Long.parseLong(id));

        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable final String id) {

        userService.deleteUser(Long.parseLong(id));

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> updateUser(@Valid @RequestBody final CreateDepositDto depositData, @PathVariable final String id) {

        final User depositedUser = userService.createDeposit(depositData, Long.parseLong(id));

        return new ResponseEntity<User>(depositedUser, HttpStatus.CREATED);

    }
}
