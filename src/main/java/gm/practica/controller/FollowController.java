package gm.practica.controller;

import gm.practica.DTOs.FollowersDTO;
import gm.practica.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
@RequestMapping("/socialMeli/v1")
public class FollowController {

    private final FollowService followService;
    private static final Logger logger = LoggerFactory.getLogger(FollowController.class);

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }


    //Endpoint para Seguir a un usuario
    @PostMapping("/follow")
    public ResponseEntity<?> followUser(@RequestBody FollowersDTO followersDTO) {
        //implementacion de un log para verificar la respuesta
        logger.info("Received follow request: {}", followersDTO);
        return new ResponseEntity<>(followService.followUser(followersDTO), HttpStatus.OK);
    }

    @PostMapping("/unfollow")
public ResponseEntity<?> unfollowUser(@RequestBody FollowersDTO followersDTO) {
    String message = followService.unfollowUser(followersDTO);
    return new ResponseEntity<>(message, HttpStatus.OK);
}

    @GetMapping("/followers/{userId}")
    public ResponseEntity<?> getFollowers(@PathVariable Long userId) {
    logger.info("Received get followers request for user: {}", userId);
    return new ResponseEntity<>(followService.getFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}")
    public ResponseEntity<?> getFollowed(@PathVariable Long userId) {
        logger.info("Received get followed request for user: {}", userId);
        return new ResponseEntity<>(followService.getFollowed(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable Long userId) {
        logger.info("Received get followers count request for user: {}", userId);
        return new ResponseEntity<>(followService.getFollowersCount(userId), HttpStatus.OK);
    }
}