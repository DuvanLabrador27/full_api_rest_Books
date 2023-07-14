package com.duvanlabrador.api_rest_full_duvan.Controller;

import com.duvanlabrador.api_rest_full_duvan.DTO.PostDTO;
import com.duvanlabrador.api_rest_full_duvan.DTO.PostResponse;
import com.duvanlabrador.api_rest_full_duvan.Service.PostService;
import com.duvanlabrador.api_rest_full_duvan.Service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return this.postService.getAllPosts(pageNumber,pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostForId(@PathVariable Long id){
        try{
            PostDTO post = this.postService.getPostForId(id);
            return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<PostDTO>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Long id){
        try{
            PostDTO post = postService.updatePost(postDTO,id);
            return new ResponseEntity<PostDTO>(post,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        try {
            postService.deletePost(id);
            return new ResponseEntity<>("Post Delete Correctly", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Conflict, The post don't exist",HttpStatus.CONFLICT);
        }
    }

}
