package com.duvanlabrador.api_rest_full_duvan.Service;

import com.duvanlabrador.api_rest_full_duvan.DTO.PostDTO;
import com.duvanlabrador.api_rest_full_duvan.Repository.PostRepository;

import java.util.List;


public interface PostService  {
    public PostDTO createPost(PostDTO postDTO);
    public List<PostDTO> getAllPosts();
    public PostDTO getPostForId(Long id);
    public PostDTO updatePost(PostDTO postDTO,Long id);
    public void deletePost(Long id);

}
