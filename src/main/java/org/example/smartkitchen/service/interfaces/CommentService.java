package org.example.smartkitchen.service.interfaces;

import org.example.smartkitchen.dto.CommentDTO;

public interface CommentService {
    CommentDTO getCommentById(Long id);
}
