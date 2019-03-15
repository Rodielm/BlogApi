package es.uv.dbcds.comments.commentsapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message
 */

@Data
@NoArgsConstructor
public class Message {

    @Min(0)
    private int id;

    @Size(min=1, max=50)
    private String title;
    
    @NotNull
    private String body;
    
    @Min(0)
    private int like;

    private List<Comment> comments;

    public Message (int id, String title, String body){
        super();
        this.id = id;
        this.title = title;
        this.body = body;
        this.comments = new ArrayList<Comment>();
        this.like = 0;
    }
}