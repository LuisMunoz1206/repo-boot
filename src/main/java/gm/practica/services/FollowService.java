package gm.practica.services;

import gm.practica.DTOs.*;
import gm.practica.entities.Follow;
import gm.practica.entities.User;
import gm.practica.exceptions.NotFoundException;
import gm.practica.repository.IFollowRepository;
import gm.practica.repository.IFollowedRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FollowService {

    @Autowired
    private IFollowRepository followRepository;

    @Autowired
    private IFollowedRepository followedRepository;

    @Autowired
    private UserService userService;

    private ModelMapper mapper = new ModelMapper();

    //funcion para seguir un usuario donde aceptara parametros o valores de entrada de tipo followersDTO
    public FollowersDTO followUser(FollowersDTO followersDTO) {



        if (followRepository.findByFollowerAndFollowed(followersDTO.getFollower(), followersDTO.getFollowed()).isPresent()) {
            throw new NotFoundException("Ya sigues a este usuario");
        }

        if(followersDTO.getFollower().equals(followersDTO.getFollowed())){
            throw new NotFoundException("No puedes seguirte a ti mismo");
        }

        if(!userService.userExists(followersDTO.getFollower())){
            throw new NotFoundException("el usuario no existe");
        }

            // Verifica si el usuario a seguir existe
            if (!userService.userExists(followersDTO.getFollowed())) {
                throw new NotFoundException("El usuario a seguir no existe");
            }

            // Verifica si el usuario que sigue existe
            if (!userService.userExists(followersDTO.getFollower())) {
                throw new NotFoundException("El usuario que sigue no existe");
            }

            // Transfomacion de followersDTO a un objeto de la entidad Follow por medio del ModelMapper
            Follow follow = mapper.map(followersDTO, Follow.class);
            // Guarda el follow en la BD por medio del metodo save
            Follow savedFollow = followRepository.save(follow);

            Optional<Follow> optionalFollow = followRepository.findById(savedFollow.getId());
            if(!optionalFollow.isPresent()){
                throw new NotFoundException("El seguimiento no existe");
            }

        //transforma y devuelve el objeto de la entidad follow a un followersDTO
        return mapper.map(savedFollow, FollowersDTO.class);
    }


    //funcion para llamar a una lista de seguidores por medio del userID
   public UserFollowersDTO getFollowers(Long userId) {

        //se llama a una lista de la entidad follow utilizando el parametro userID
    List<Follow> follows = followRepository.findByFollower(userId);
        //se crea una nueva lista donde se va a contener la nueva lista de followersListDTOs
    List<FollowersListDTO> followersListDTO = new ArrayList<>();

    //iteracion o ciclo para crear la nueva lista de followersListDTO
    for (Follow follow : follows) {
        //creacion de una variable para la almacenar la informacion del usuario seguido
        FollowersListDTO dto = new FollowersListDTO();
        // asigna valores de la informacion del usuario seguido
        dto.setUserId(follow.getFollowed());
        dto.setUserName(userService.getUserName(follow.getFollowed()));
        // crea la lista y almacena toda la info de follows
        followersListDTO.add(dto);

    }
       if(follows.isEmpty()){
           throw new NotFoundException("no existen usuarios seguidos");
       }

    //mapa que devuelve la informacion del follow
    UserFollowersDTO response = new UserFollowersDTO();// es una estructura de datos que almacena pares de clave-valor
    // Se añade un par clave-valor al mapa,
    response.setUserId( userId); //esto especifica el ID del usuario de los seguidores se están consultando
    response.setUserName(userService.getUserName(userId));// esto especifica el nombre del usuario de los seguidores se están consultando
    response.setFollowers(followersListDTO);// esto especifica los DTOs de cada seguidor, almacenados previamente con sus respectivos IDs y nombres.

       //responde el mapa de con los detalles de los usuarios y los seguidores
    return response;
}

    public UserFollowedDTO getFollowed(Long userId) {
        List<Follow> follows = followedRepository.findByFollowed(userId);
        List<FollowedListDTO> followedListDTO = new ArrayList<>();

        for (Follow follow : follows) {
            FollowedListDTO dto = new FollowedListDTO();
            dto.setUserId(follow.getFollower());
            dto.setUserName(userService.getUserName(follow.getFollower()));
            followedListDTO.add(dto);
        }

        if(follows.isEmpty()){
            throw new NotFoundException("no existen usuarios seguidos");
        }

        UserFollowedDTO response = new UserFollowedDTO();
        response.setUserId( userId); //esto especifica el ID del usuario de los seguidores se están consultando
        response.setUserName(userService.getUserName(userId));// esto especifica el nombre del usuario de los seguidores se están consultando
        response.setFollowed(followedListDTO);// esto especifica los DTOs de cada seguidor, almacenados previamente con sus respectivos IDs y nombres.


        return response;
    }

    //funcion para devolver un mapa que hace el conteneo de los seguidores a un suaurio especifico
    public CountFollowersDTO getFollowersCount(Long userId) {
        //se llama a una lista de la entidad follow utilizando el parametro userID
    List<Follow> follows = followRepository.findByFollower(userId);

    if(follows.isEmpty()){
        throw new NotFoundException("no existen usuarios seguidos");
    }


    CountFollowersDTO response = new CountFollowersDTO();
    response.setUserId(userId);
    response.setUserName(userService.getUserName(userId));
    response.setFollowersCount(follows.size());

    return response;
}


public String unfollowUser(FollowersDTO followersDTO) {
    // Busca el registro de seguimiento en la base de datos
    Optional<Follow> optionalFollow = followRepository.findByFollowerAndFollowed(followersDTO.getFollower(), followersDTO.getFollowed());

    if (!optionalFollow.isPresent()) {
        throw new NotFoundException("No estás siguiendo a este usuario");
    }

    // Cambia el estado del seguimiento a inactivo
    Follow follow = optionalFollow.get();
    follow.setActive(false);

    // Guarda el cambio en la base de datos
    followRepository.save(follow);

    return "Haz dejado de seguir al usuario con ID: " + followersDTO.getFollowed();
}


}

