package com.example.board_project.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Modifying
    @Query(value = "INSERT INTO image(boardId, postImageUrl, createDate) VALUES (:boardId, :postImageUrl, now())", nativeQuery = true)
    void insertImage(String postImageUrl, int boardId);

}
