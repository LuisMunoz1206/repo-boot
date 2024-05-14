
package gm.practica.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import gm.practica.DTOs.FollowersDTO;
import gm.practica.DTOs.UserDTO;
import gm.practica.entities.Follow;
import gm.practica.entities.User;
import gm.practica.exceptions.NotFoundException;
import gm.practica.repository.IUsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUsersRepository usersRepository;
    private ModelMapper mapper = new ModelMapper();
    @Override
    public List<UserDTO> getUsers() {
        List<User> users = usersRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("No hay usuarios");
        }
        return users.stream()
                .map(user -> mapper.map(user,UserDTO.class))
                .toList();
    }

    String getUserName(Long userId) {
        Optional<User> user = usersRepository.findById(userId);
        return user.map(User::getNameUser).orElse("");
    }

    public boolean userExists(Long userId) {
    return usersRepository.existsById(userId);
    }

}
