package com.duvanlabrador.api_rest_full_duvan.Controller;

import com.duvanlabrador.api_rest_full_duvan.DTO.PostDTO;
import com.duvanlabrador.api_rest_full_duvan.Service.PostService;
import com.duvanlabrador.api_rest_full_duvan.Service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO){

        try{
            PostDTO post = this.postService.createPost(postDTO);
            return new ResponseEntity<PostDTO>(post,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<PostDTO>(HttpStatus.CONFLICT);
        }
    }

}
