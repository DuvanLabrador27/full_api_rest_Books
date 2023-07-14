package com.duvanlabrador.api_rest_full_duvan.Service;

import com.duvanlabrador.api_rest_full_duvan.DTO.CommentsDTO;
import com.duvanlabrador.api_rest_full_duvan.Entity.CommentsEntity;
import com.duvanlabrador.api_rest_full_duvan.Entity.PostEntity;
import com.duvanlabrador.api_rest_full_duvan.Repository.CommentsRepository;
import com.duvanlabrador.api_rest_full_duvan.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository, PostRepository postRepository){
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentsDTO createComment(Long post_Id, CommentsDTO commentsDTO) {
        CommentsEntity commentsEntity = mapToEntity(commentsDTO);

        PostEntity postEntity = postRepository.findById(post_Id).orElseThrow();

        commentsEntity.setPostEntity(postEntity);

        CommentsEntity newComment = commentsRepository.save(commentsEntity);

        return mapToDTO(newComment);

    }

    @Override
    public List<CommentsDTO> getCommentById(Long post_Id) {
        List<CommentsEntity> commentsEntities = commentsRepository.findByPostEntityId(post_Id);
        return  commentsEntities.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentsDTO getCommentForId(Long post_Id, Long commentId) {
        PostEntity post = postRepository.findById(post_Id).orElseThrow();
        CommentsEntity commentsEntity = commentsRepository.findById(commentId).orElseThrow();
        if (!commentsEntity.getPostEntity().getId().equals(post.getId())){
            throw new RuntimeException("the commentary don't belong to post");
        }
        return mapToDTO(commentsEntity);
    }

    @Override
    public CommentsDTO updateComment(Long post_id, CommentsDTO commentsDTO, Long commentId) {
        PostEntity postEntity = postRepository.findById(post_id).orElseThrow();
        CommentsEntity commentsEntity = commentsRepository.findById(commentId).orElseThrow();
        if (!commentsEntity.getPostEntity().getId().equals(postEntity.getId())){
            throw new RuntimeException("the commentary don't belong to post");
        }
        commentsEntity.setCommentBody(commentsDTO.getCommentBody());
        commentsEntity.setEmail(commentsDTO.getEmail());
        commentsEntity.setName(commentsDTO.getName());
        CommentsEntity updateComment = commentsRepository.save(commentsEntity);
        return mapToDTO(updateComment);
    }

    @Override
    public void deleteComment(Long post_id, Long commentId) {
        PostEntity postEntity = postRepository.findById(post_id).orElseThrow();
        CommentsEntity commentsEntity = commentsRepository.findById(commentId).orElseThrow();
        if (!commentsEntity.getPostEntity().getId().equals(postEntity.getId())){
            throw new RuntimeException("the commentary don't belong to post");
        }
        commentsRepository.delete(commentsEntity);
    }

    private CommentsDTO mapToDTO(CommentsEntity comments){
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setId(comments.getId());
        commentsDTO.setCommentBody(comments.getCommentBody());
        commentsDTO.setEmail(comments.getEmail());
        commentsDTO.setName(comments.getName());

        return commentsDTO;
    }

    private CommentsEntity mapToEntity(CommentsDTO commentsDTO){
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setCommentBody(commentsDTO.getCommentBody());
        commentsEntity.setEmail(commentsDTO.getEmail());
        commentsEntity.setName(commentsDTO.getName());

        return commentsEntity;
    }

}
