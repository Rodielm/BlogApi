package es.uv.dbcds.comments.commentsapi.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uv.dbcds.comments.commentsapi.domain.Comment;
import es.uv.dbcds.comments.commentsapi.service.MessageService;

/**
 * CommentsController
 */

@RestController
@RequestMapping("api/")
public class CommentsController {

    @Autowired
    private MessageService messageService;

    public CommentsController(MessageService mensajeService) {
        this.messageService = mensajeService;
    }

    @GetMapping("comments/{idMensaje}/{idComments}")
    public ResponseEntity<Optional<Comment>> getCommentById(@PathVariable("idMensaje") int idMessage,
            @PathVariable("idComments") int idComments) {

        Optional<Comment> c = messageService.getCommentById(idMessage, idComments);
        if (!c.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping("comments/{idMessage}")
    public ResponseEntity<Comment> addComentario(@PathVariable("idMessage") int idMessage,
            @RequestBody Comment comment) {
        messageService.addComment(idMessage, comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("comments/{idMessage}")
    public ResponseEntity<Comment> updateComentario(@PathVariable("idMessage") int idMessage,
            @RequestBody Comment comment) {
                messageService.updateComentario(idMessage, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("comments/{idMessage}/{idComment}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("idMessage") int idMessage,
            @PathVariable int idComment) {
        messageService.deleteCommnets(idMessage, idComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}