package es.uv.dbcds.comments.commentsapi.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uv.dbcds.comments.commentsapi.domain.Message;
import es.uv.dbcds.comments.commentsapi.service.MessageResourceAssembler;
import es.uv.dbcds.comments.commentsapi.service.MessageService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
/**
 * MensajeApi
 */

@RestController
@RequestMapping("api/messages")
public class MessageController {

    MessageResourceAssembler assembler;

    @Autowired
    private MessageService messageService;

    MessageController(MessageResourceAssembler assembler){
        this.assembler = assembler;
    }

    @GetMapping
    public Resources<Resource<Message>> getAll() {

        List<Resource<Message>> messages = messageService.getMessages().stream()
        .map(assembler::toResource)
        .collect(Collectors.toList());

        return new Resources<>(messages,
        linkTo(methodOn(MessageController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<Message> getMessage(@PathVariable("id") int id) {
        Message m = messageService.getMessageById(id);
        return assembler.toResource(m);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Message addMessage(@RequestBody @Valid Message message) {
        return messageService.addMessage(message);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMessage(@PathVariable("id") int idMessage) {
        messageService.deleteMessage(idMessage);
    }

    @PutMapping(value = "/{id}")
    public Message updateMessage(@PathVariable("id") int id, @RequestBody Message message) {
        return messageService.updateMessage(id, message);
    
    }

    @PutMapping(value = "/{id}/like")
    public Message addLikeMessage(@PathVariable("id") int id) {
        return messageService.addLiketoMessage(id);
    }

    // @GetMapping("messages/{id}")
    // public ResponseEntity<Optional<Message>> getMensajeById(@PathVariable("id")
    // int id) {
    // Optional<Message> m = mensajeService.getMessageById(id);
    // if (!m.isPresent()) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    // return new ResponseEntity<>(m, HttpStatus.OK);
    // }

    // @PostMapping("mensaje")
    // public ResponseEntity<Message> addMensaje(@RequestBody Message message) {
    // mensajeService.addMensaje(message);
    // return new ResponseEntity<>(HttpStatus.CREATED);
    // }

}