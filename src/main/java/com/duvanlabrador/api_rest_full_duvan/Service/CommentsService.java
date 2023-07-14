package com.duvanlabrador.api_rest_full_duvan.Service;

import com.duvanlabrador.api_rest_full_duvan.DTO.CommentsDTO;

import java.util.List;

public interface CommentsService {
    public CommentsDTO createComment(Long post_Id, CommentsDTO commentsDTO);
    public List<CommentsDTO> getCommentById(Long post_Id);
    public CommentsDTO getCommentForId(Long post_Id, Long commentId);
    public CommentsDTO updateComment(Long post_id, CommentsDTO commentsDTO, Long commentId);
    public void deleteComment(Long post_id, Long commentId);
}
