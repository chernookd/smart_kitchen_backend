package org.example.smartkitchen.controllers.api.comment;

import org.example.smartkitchen.dto.comment.CommentDTO;
import org.example.smartkitchen.service.interfaces.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(commentService.getCommentById(id));
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment, @PathVariable Long id) {
        return ResponseEntity.ok().body(commentService.createComment(comment, id));
    }

}
