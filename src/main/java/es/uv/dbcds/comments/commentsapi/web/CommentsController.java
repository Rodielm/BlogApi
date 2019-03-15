package es.uv.dbcds.comments.commentsapi.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uv.dbcds.comments.commentsapi.domain.Comment;
import es.uv.dbcds.comments.commentsapi.service.CommentResourceAssembler;
import es.uv.dbcds.comments.commentsapi.service.MessageService;

/**
 * CommentsController
 */

@RestController
@RequestMapping("api/messages/{id}/comments")
public class CommentsController {

    @Autowired
    private MessageService messageService;

    CommentResourceAssembler assembler;

    CommentsController(CommentResourceAssembler assembler){
        this.assembler = assembler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Comment> addComment(@PathVariable("id") int idMessage, @RequestBody @Valid Comment comment) {
         Comment c = messageService.addComment(idMessage, comment);
         return assembler.toResource(c);
    }

    @PutMapping("/{idComment}")
    public Comment updateComment(@PathVariable("id") int idMessage,@PathVariable("idComment") int idComment,@RequestBody Comment comment) {
        return messageService.updateComment(idMessage,idComment,comment);
    }

    @DeleteMapping("/{idComment}")
    public void deleteComment(@PathVariable("id") int idMessage, @PathVariable("idComment") int idComment) {
        messageService.deleteComment(idMessage, idComment);
    }


    @PutMapping(value = "/{idComment}/like")
    public Comment addLikeComment(@PathVariable("id") int id,@PathVariable("idComment") int idComment) {
        return messageService.addLiketoComment(id,idComment);
    }

        // @GetMapping("/{idComments}")
    // public ResponseEntity<Optional<Comment>> getCommentById(@PathVariable("idMensaje") int idMessage,
    //         @PathVariable("idComments") int idComments) {

    //     Optional<Comment> c = messageService.getCommentById(idMessage, idComments);
    //     if (!c.isPresent()) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }

    //     return new ResponseEntity<>(c, HttpStatus.OK);
    // }

    // @PostMapping()
    // public ResponseEntity<Comment> addComentario(@PathVariable("idMessage") int
    // idMessage,
    // @RequestBody Comment comment) {
    // messageService.addComment(idMessage, comment);
    // return new ResponseEntity<>(HttpStatus.CREATED);
    // }

}