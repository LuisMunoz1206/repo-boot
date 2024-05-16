package gm.practica.services;

import gm.practica.DTOs.*;
import gm.practica.entities.Follow;
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

    public FollowersDTO followUser(FollowersDTO followersDTO) {
        Follow follow = mapper.map(followersDTO, Follow.class);
        Follow savedFollow = followRepository.save(follow);

        if (followRepository.findByFollowerAndFollowed(followersDTO.getFollower(), followersDTO.getFollowed()).isPresent()) {
            throw new NotFoundException("Ya sigues a este usuario");
        }

        if(followersDTO.getFollower().equals(followersDTO.getFollowed())){
            throw new NotFoundException("No puedes seguirte a ti mismo");
        }

        if(!userService.userExists(followersDTO.getFollower())){
            throw new NotFoundException("el usuario no existe");
        }

        if (!userService.userExists(followersDTO.getFollowed())) {
            throw new NotFoundException("El usuario a seguir no existe");
        }

        Optional<Follow> optionalFollow = followRepository.findById(savedFollow.getId());
        if(!optionalFollow.isPresent()){
            throw new NotFoundException("El seguimiento no existe");
        }

        return mapper.map(savedFollow, FollowersDTO.class);
    }

   public UserFollowersDTO getFollowers(Long userId) {
    List<Follow> follows = followRepository.findByFollower(userId);
    List<FollowersListDTO> followersListDTO = new ArrayList<>();

    for (Follow follow : follows) {
        FollowersListDTO dto = new FollowersListDTO();
        dto.setUserId(follow.getFollowed());
        dto.setUserName(userService.getUserName(follow.getFollowed()));
        followersListDTO.add(dto);
        }
   if(follows.isEmpty()){
       throw new NotFoundException("no existen usuarios seguidos");
   }

    UserFollowersDTO response = new UserFollowersDTO();
    response.setUserId( userId);
    response.setUserName(userService.getUserName(userId));
    response.setFollowers(followersListDTO);
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
        response.setUserId( userId);
        response.setUserName(userService.getUserName(userId));
        response.setFollowed(followedListDTO);
        return response;
    }

    public CountFollowersDTO getFollowersCount(Long userId) {
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
    Optional<Follow> optionalFollow = followRepository.findByFollowerAndFollowed(followersDTO.getFollower(), followersDTO.getFollowed());

    if (!optionalFollow.isPresent()) {
        throw new NotFoundException("No estás siguiendo a este usuario");
    }

    Follow follow = optionalFollow.get();
    follow.setActive(false);
    followRepository.save(follow);
    return "Haz dejado de seguir al usuario con ID: " + followersDTO.getFollowed();
    }
}

