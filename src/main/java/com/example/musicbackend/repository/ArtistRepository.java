package com.example.musicbackend.repository;

import com.example.musicbackend.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
    Artist findByName(String name);
    @Query("select a from Artist a where a.name like %:keyword%")
    List<Artist> searchArtistByName(String keyword);
}
