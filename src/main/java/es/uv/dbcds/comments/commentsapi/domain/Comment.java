package es.uv.dbcds.comments.commentsapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Comments
 */

@Getter
@Setter
@AllArgsConstructor
public class Comment {
    private int id;
    private String name;
    private String email;
    private String text;

}