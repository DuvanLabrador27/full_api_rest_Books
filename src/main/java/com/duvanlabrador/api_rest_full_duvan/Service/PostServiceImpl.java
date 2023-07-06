package com.duvanlabrador.api_rest_full_duvan.Service;

import com.duvanlabrador.api_rest_full_duvan.DTO.PostDTO;
import com.duvanlabrador.api_rest_full_duvan.Entity.PostEntity;
import com.duvanlabrador.api_rest_full_duvan.Excepcion.ResourceNotFoundException;
import com.duvanlabrador.api_rest_full_duvan.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public PostDTO createPost(PostDTO postDTO) {
            //Convertimos DTO A ENTIDAD
            PostEntity postEntity = mapEntity(postDTO);
            //Guardamos los datos en la entidad
            PostEntity newPost = postRepository.save(postEntity);
            //Los datos almacenados los convertimos a DTO
            PostDTO answerPost= mapDTO(newPost);


        return answerPost;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        //Le pedimos a la BD que me devuelva todos los datos
        List<PostEntity> postEntities = postRepository.findAll();
        //Convertimos de Entity a DTO
        return postEntities.stream().map(post -> mapDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostForId(Long id) {
        /**Le pido a la entidad que me busque por ID, sino lo encuentra que me
         Retorne una excepcion
         */
        PostEntity postEntities = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post","id",id));
        //Si lo encuentra que me retorne DTO
        return mapDTO(postEntities);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO,Long id) {
        /**Le pido a la entidad que me busque por ID, sino lo encuentra que me
         Retorne una excepcion
         */
        PostEntity postEntities = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post","id",id));

        postEntities.setTitle(postDTO.getTitle());
        postEntities.setDescription(postDTO.getDescription());
        postEntities.setContent(postDTO.getContent());

        PostEntity updatePost = postRepository.save(postEntities);
        return mapDTO(updatePost);

    }

    @Override
    public void deletePost(Long id) {

        /**Le pido a la entidad que me busque por ID, sino lo encuentra que me
         Retorne una excepcion
         */
        PostEntity postEntities = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post","id",id));
        postRepository.delete(postEntities);
    }

    //Convert DTO to ENTITY
    private PostEntity mapEntity(PostDTO postDTO){
        //Create a object
        PostEntity postToEntity = new PostEntity();
        //Convert DTO to ENTITY
        postToEntity.setTitle(postDTO.getTitle());
        postToEntity.setDescription(postDTO.getDescription());
        postToEntity.setContent(postDTO.getContent());
        return postToEntity;
    }

    //Convert ENTITY to DTO
    private PostDTO mapDTO(PostEntity postEntity) {
        //Create a object
        PostDTO postToDTO = new PostDTO();
        //Convert ENTITY to DTO
        postToDTO.setId(postEntity.getId());
        postToDTO.setTitle(postEntity.getTitle());
        postToDTO.setDescription(postEntity.getDescription());
        postToDTO.setContent(postEntity.getContent());
        return postToDTO;
    }


}
