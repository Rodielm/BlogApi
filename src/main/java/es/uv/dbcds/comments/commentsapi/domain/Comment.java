package es.uv.dbcds.comments.commentsapi.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * Comments
 */

@Getter
@Setter
public class Comment {

    @Min(0)
    private int id;

    @NotNull
    @Size(min = 1)
    private String author;

    @NotNull
    @Size(min = 1)
    private String text;

    @JsonIgnore
    private Message parent;

    public Comment(int id, String author, String text) {
        super();
        this.id = id;
        this.author = author;
        this.text = text;
    }
}