package com.duvanlabrador.api_rest_full_duvan.Controller;

import com.duvanlabrador.api_rest_full_duvan.DTO.CommentsDTO;
import com.duvanlabrador.api_rest_full_duvan.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsController {

    private final CommentsService commentsService;
    @Autowired
    public CommentsController(CommentsService commentsService){
        this.commentsService = commentsService;
    }

    @GetMapping("/post/{post_Id}/comments")
    public List<CommentsDTO> listCommentByPost(@PathVariable(value = "post_Id") Long post_Id){
        return commentsService.getCommentById(post_Id);
    }
    @GetMapping("/post/{post_Id}/comments/{commentId}")
    public ResponseEntity<CommentsDTO> getPostByCommentForId(
            @PathVariable(value = "post_Id") Long post_Id,
            @PathVariable(value = "commentId") Long commentId){
        try {
            CommentsDTO commentsDTO = commentsService.getCommentForId(post_Id,commentId);
            return new ResponseEntity<>(commentsDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentsDTO> createdComment(
            @PathVariable(value = "postId") long postId,
            @RequestBody CommentsDTO commentsDTO){
            try{
                CommentsDTO comment = commentsService.createComment(postId,commentsDTO);
                return new ResponseEntity<CommentsDTO>(comment, HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
    }
    @PutMapping("/post/{post_Id}/comments/{commentId}")
    public ResponseEntity<CommentsDTO> updateComments(
            @PathVariable(value = "post_Id") Long post_Id,
            @RequestBody CommentsDTO commentsDTO,
            @PathVariable(value = "commentId") Long commentId){
        try {
            CommentsDTO comment = commentsService.updateComment(post_Id,commentsDTO,commentId);
            return new ResponseEntity<CommentsDTO>(comment,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<CommentsDTO>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/post/{post_Id}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "post_Id") Long post_Id,
            @PathVariable(value = "commentId") Long commentId){
        try {
            commentsService.deleteComment(post_Id,commentId);
            return new ResponseEntity<>("The comment already delete correctly",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
