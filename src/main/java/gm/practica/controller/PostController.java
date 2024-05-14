package gm.practica.controller;


import gm.practica.DTOs.PostDTO;
import gm.practica.DTOs.ProductDTO;
import gm.practica.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socialMeli/v1")
public class PostController {

    @Autowired
    private PostService postService;

   @PostMapping("/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
    return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.OK);
    }

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<?> getProductsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(postService.getProductsByUser(userId), HttpStatus.OK);
    }
}