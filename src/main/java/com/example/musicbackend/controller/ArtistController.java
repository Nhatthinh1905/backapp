package com.example.musicbackend.controller;

import com.example.musicbackend.model.Artist;
import com.example.musicbackend.service.ArtistService;
import com.example.musicbackend.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ImageUploadService imageUploadService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllArtist(){
        return new ResponseEntity<>(artistService.getAllArtist(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Artist> uploadArtistImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name
    ) throws IOException {
        String imageUrl = imageUploadService.uploadImage(file, "img_artist");

        Artist artist = new Artist();
        artist.setName(name);
        artist.setImageUrl(imageUrl);
        artistService.saveArtist(artist);

        return ResponseEntity.ok(artist);
    }
    @GetMapping("/{artistId}/tracks")
    public ResponseEntity<?> getAllTracksOfArtist(@PathVariable Long artistId){
        return new ResponseEntity<>(artistService.getAllTracksOfArtist(artistId),HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchArtist(@RequestParam("keyword") String keyword){
        return new ResponseEntity<>(artistService.searchByName(keyword),HttpStatus.OK);
    }
}
