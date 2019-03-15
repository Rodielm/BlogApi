package es.uv.dbcds.comments.commentsapi.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Comments
 */

@Data
@NoArgsConstructor
public class Comment {

    @Min(0)
    private int id;

    @NotNull
    @Size(min = 1)
    private String author;

    @NotNull
    @Size(min = 1)
    private String text;

    @Min(0)
    private int like;

    @JsonIgnore
    private Message parent;

    public Comment(int id, String author, String text) {
        super();
        this.id = id;
        this.author = author;
        this.text = text;
        this.like = 0;
    }
}