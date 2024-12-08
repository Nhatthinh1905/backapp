package com.example.musicbackend.repository;

import com.example.musicbackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {
    @Query("select a from Album a where a.title =:name")
    Album findByName(String name);
    @Query("select a from Album a where a.title like %:keyword%")
    List<Album> searchAlbumByTitle(String keyword);
}
