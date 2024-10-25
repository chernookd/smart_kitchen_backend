package org.example.smartkitchen.service.impl.comment;

import org.example.smartkitchen.domain.entity.comment.CommentEntity;
import org.example.smartkitchen.domain.entity.user.UserEntity;
import org.example.smartkitchen.domain.repository.comment.CommentRepository;
import org.example.smartkitchen.domain.repository.user.UserRepository;
import org.example.smartkitchen.dto.comment.CommentDTO;
import org.example.smartkitchen.exceptions.CommentNotFoundException;
import org.example.smartkitchen.exceptions.UserNotFoundException;
import org.example.smartkitchen.service.interfaces.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    public CommentDTO getCommentById(Long id) throws CommentNotFoundException {
        Optional<CommentEntity> commentEntity = commentRepository.findById(id);
        if (commentEntity.isPresent()) {
            return new CommentDTO(commentEntity.get().getComment(), commentEntity.get().getUser().getId());
        }

        throw new CommentNotFoundException("comment not found");
    }

    public CommentDTO createComment(CommentDTO comment, Long userId) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        UserEntity userEntity = optionalUserEntity.get();

        CommentEntity commentEntity = new CommentEntity(null, comment.getComment(), userEntity);

        CommentEntity savedEntity = commentRepository.save(commentEntity);

        return new CommentDTO(savedEntity.getComment(), savedEntity.getUser().getId());
    }


}
