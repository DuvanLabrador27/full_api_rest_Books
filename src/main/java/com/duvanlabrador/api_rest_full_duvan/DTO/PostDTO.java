package com.duvanlabrador.api_rest_full_duvan.DTO;

import com.duvanlabrador.api_rest_full_duvan.Entity.CommentsEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentsEntity> commentsEntities = new HashSet<>();
}
