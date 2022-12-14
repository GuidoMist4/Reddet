package net.bcsoft.com.Reddet.controller;


import lombok.AllArgsConstructor;
import net.bcsoft.com.Reddet.DTO.PostRequest;
import net.bcsoft.com.Reddet.DTO.PostResponse;
import net.bcsoft.com.Reddet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/Api/posts/")

public class PostController {

    @Autowired
    private PostService postService;

    //creazione post
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //prende tutti i post
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    //prende un post specifico (passiamo l'id)
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        return status(HttpStatus.OK).body(postService.getPost(postId));
    }

    //prende tutti i post per subreddet(tramite l'id del subreddet)
    @GetMapping("by-subreddet/{subId}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddet(Long subId) {
        return status(HttpStatus.OK).body(postService.getPostsBySubreddet(subId));
    }

    //prende tutti i post di un utente specifico (passiamo id del user)
    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(String username) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }
}
