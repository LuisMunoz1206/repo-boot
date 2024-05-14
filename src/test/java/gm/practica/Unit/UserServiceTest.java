package gm.practica.Unit;

import gm.practica.DTOs.UserDTO;
import gm.practica.entities.User;
import gm.practica.repository.IUsersRepository;
import gm.practica.services.IUserService;
import gm.practica.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserServiceTest {

    @Test
    public void testGetUser() throws IOException {

        IUserService userService = new UserService();

        List<UserDTO> usersExpected = List.of(
                new UserDTO(1L, "Juan Perez"),
                new UserDTO(2L, "Maria Gomez")
        );

        List<UserDTO> usersActual = userService.getUsers();

        Assertions.assertEquals(usersExpected, usersActual);


    }
}