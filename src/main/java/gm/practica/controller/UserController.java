package gm.practica.controller;


import gm.practica.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socialMeli/v1")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }


}
