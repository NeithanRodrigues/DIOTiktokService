package com.dio.nathan.controller;

import com.dio.nathan.model.Video;
import com.dio.nathan.service.SeleniumService;
import com.dio.nathan.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("videos")
public class VideoRestController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/scrap")
    public void scrapLink(@RequestParam String url) {
        Video video = videoService.startSelenium(url);
        this.create(video);
    }

    @GetMapping
    public ResponseEntity<Iterable<Video>> getVideos() {
        return ResponseEntity.ok(videoService.getVideos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> read(@PathVariable long id) {
        return ResponseEntity.ok(videoService.read(id));
    }

    @PostMapping
    public ResponseEntity<Video> create(@RequestBody Video video) {
        videoService.create(video);
        return ResponseEntity.ok(video);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> update(@PathVariable long id, @RequestBody Video video) {
        videoService.update(id, video);
        return ResponseEntity.ok(video);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        videoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
