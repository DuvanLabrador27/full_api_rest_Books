package com.duvanlabrador.api_rest_full_duvan.Service;

import com.duvanlabrador.api_rest_full_duvan.DTO.PostDTO;
import com.duvanlabrador.api_rest_full_duvan.Entity.PostEntity;
import com.duvanlabrador.api_rest_full_duvan.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public PostDTO createPost(PostDTO postDTO) {
        //Covertimos de DTO a Entities
        PostEntity postToEntity = new PostEntity();
        postToEntity.setTitle(postDTO.getTitle());
        postToEntity.setDescription(postDTO.getDescription());
        postToEntity.setContent(postDTO.getContent());

        PostEntity newPost = postRepository.save(postToEntity);

        //Convertimos de ENTITIES a DTO
        PostDTO postToDTO = new PostDTO();
        postToDTO.setId(newPost.getId());
        postToDTO.setTitle(newPost.getTitle());
        postToDTO.setDescription(newPost.getDescription());
        postToDTO.setContent(newPost.getContent());

        return postToDTO;
    }
}
