package com.duvanlabrador.api_rest_full_duvan.Repository;

import com.duvanlabrador.api_rest_full_duvan.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

}
