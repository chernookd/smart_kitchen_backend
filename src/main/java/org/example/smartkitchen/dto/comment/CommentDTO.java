package org.example.smartkitchen.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {


    String comment;

    Long user_id;
}
