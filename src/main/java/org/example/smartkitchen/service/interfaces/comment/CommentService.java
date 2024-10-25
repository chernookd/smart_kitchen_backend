package org.example.smartkitchen.service.interfaces.comment;

import org.example.smartkitchen.dto.comment.CommentDTO;

public interface CommentService {
    CommentDTO getCommentById(Long id);

    CommentDTO createComment(CommentDTO comment, Long userId);

}
