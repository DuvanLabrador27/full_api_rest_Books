package com.duvanlabrador.api_rest_full_duvan.Repository;

import com.duvanlabrador.api_rest_full_duvan.Entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity,Long> {
    public List<CommentsEntity> findByPostEntityId(Long post_Id);
}
