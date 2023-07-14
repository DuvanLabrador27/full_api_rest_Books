package com.duvanlabrador.api_rest_full_duvan.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean lastPage;

}
