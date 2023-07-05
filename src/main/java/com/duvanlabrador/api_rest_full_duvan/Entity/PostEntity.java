package com.duvanlabrador.api_rest_full_duvan.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;

}
