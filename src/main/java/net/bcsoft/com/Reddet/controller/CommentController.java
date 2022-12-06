package net.bcsoft.com.Reddet.controller;

import lombok.AllArgsConstructor;
import net.bcsoft.com.Reddet.DTO.CommentDTO;
import net.bcsoft.com.Reddet.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment/")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDTO commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsForUser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForUser(username));
    }

}
