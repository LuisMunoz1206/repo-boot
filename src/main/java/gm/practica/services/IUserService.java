package gm.practica.services;

import gm.practica.DTOs.FollowersDTO;
import gm.practica.DTOs.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getUsers();

}
