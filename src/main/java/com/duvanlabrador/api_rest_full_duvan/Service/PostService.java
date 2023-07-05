package com.duvanlabrador.api_rest_full_duvan.Service;

import com.duvanlabrador.api_rest_full_duvan.DTO.PostDTO;
import com.duvanlabrador.api_rest_full_duvan.Repository.PostRepository;


public interface PostService  {
    public PostDTO createPost(PostDTO postDTO);
}
