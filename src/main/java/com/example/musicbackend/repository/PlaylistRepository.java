package com.example.musicbackend.repository;

import com.example.musicbackend.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    @Query("select pl from Playlist pl where pl.user.id = :userId")
    List<Playlist> findAllPlaylistbyUserId(@Param("userId") Long userId);
    Playlist findByName(String name);

}
