package es.uv.dbcds.comments.commentsapi.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Message
 */

@Setter
@Getter
@AllArgsConstructor
public class Message {

    private int id;
    private String title;
    private String body;
    private List<Comment> comments;
}