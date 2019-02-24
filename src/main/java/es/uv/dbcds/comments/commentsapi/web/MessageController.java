package es.uv.dbcds.comments.commentsapi.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import es.uv.dbcds.comments.commentsapi.service.MessageService;

/**
 * MensajeApi
 */

@RestController
@RequestMapping("api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getAll() {
        return messageService.getMessages();
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable("id") int id) {
        return messageService.getMessageById(id);
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