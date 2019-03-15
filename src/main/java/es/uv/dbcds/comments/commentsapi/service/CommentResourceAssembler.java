package es.uv.dbcds.comments.commentsapi.service;

/**
 * CommentResourceAssembler
 */

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import es.uv.dbcds.comments.commentsapi.domain.Comment;
import es.uv.dbcds.comments.commentsapi.web.CommentsController;
import es.uv.dbcds.comments.commentsapi.web.MessageController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class CommentResourceAssembler implements ResourceAssembler<Comment, Resource<Comment>> {

    @Override
    public Resource<Comment> toResource(Comment c) {
        return new Resource<>(c,
        linkTo(methodOn(CommentsController.class).addLikeComment(c.getParent().getId(),c.getId())).withSelfRel());
    }

}