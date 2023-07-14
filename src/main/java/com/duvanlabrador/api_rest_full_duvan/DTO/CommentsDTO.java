package com.duvanlabrador.api_rest_full_duvan.DTO;

import lombok.Data;

@Data
public class CommentsDTO {
    private Long id;
    private String commentBody;
    private String email;
    private String name;
}
