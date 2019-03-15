package es.uv.dbcds.comments.commentsapi.service;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import es.uv.dbcds.comments.commentsapi.domain.Message;
import es.uv.dbcds.comments.commentsapi.web.MessageController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * MessageResourceAssembler
 */

@Component
public class MessageResourceAssembler implements ResourceAssembler<Message, Resource<Message>> {

    @Override
    public Resource<Message> toResource(Message m) {
        return new Resource<>(m,
        linkTo(methodOn(MessageController.class).getMessage(m.getId())).withSelfRel(),
        linkTo(methodOn(MessageController.class).addLikeMessage(m.getId())).withSelfRel(),
        linkTo(methodOn(MessageController.class).getAll()).withRel("messages"));
    }


    
}