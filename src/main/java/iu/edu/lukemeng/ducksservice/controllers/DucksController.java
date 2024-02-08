package iu.edu.lukemeng.ducksservice.controllers;

import iu.edu.lukemeng.ducksservice.model.Duck;
import iu.edu.lukemeng.ducksservice.model.DuckData;
import iu.edu.lukemeng.ducksservice.model.DuckType;
import iu.edu.lukemeng.ducksservice.repository.DucksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.io.IOException;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/ducks")
public class DucksController {
    private DucksRepository ducksRepository;

    public DucksController(DucksRepository ducksRepository) {
        this.ducksRepository = ducksRepository;
    }

    @PostMapping
    public boolean add(@RequestBody DuckData duck) {
        try {
            return ducksRepository.add(duck);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllDucks() {
        System.out.println("heylolLOL HERE");
        try {
            List<Duck> ducks = ducksRepository.getAllDucks();
            return new ResponseEntity<>(ducks, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuckData> find(@PathVariable int id) {
        try {
            DuckData duck = ducksRepository.getDuck(id);
            if(duck != null) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(duck);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/{id}/image")
    public boolean updateImage(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        System.out.println(file);
        return true;
//        try {
//            return ducksRepository.updateImage(id, file);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }



    @GetMapping("/{id}/image")
    public ResponseEntity<?> downloadImage(@PathVariable int id) {
        try {
            byte[] image = ducksRepository.downloadImage(id, id + ".png");
            return image != null ? ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/audio")
    public ResponseEntity<?> uploadAudio(@PathVariable int id, @RequestParam MultipartFile audio) {
        try {
            boolean result = ducksRepository.uploadAudio(audio, id);
            return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/audio")
    public ResponseEntity<?> downloadAudio(@PathVariable int id) {
        try {
            byte[] audio = ducksRepository.downloadAudio(id, id + ".mp3");
            return audio != null ? ResponseEntity.ok().contentType(MediaType.valueOf("audio/mpeg")).body(audio) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam DuckType type) {
        try {
            List<DuckData> ducks = ducksRepository.search(type.toString());
            return new ResponseEntity<>(ducks, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
