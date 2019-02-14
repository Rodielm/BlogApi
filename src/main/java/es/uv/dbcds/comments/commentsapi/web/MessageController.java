package es.uv.dbcds.comments.commentsapi.web;

import org.springframework.web.bind.annotation.RestController;

import es.uv.dbcds.comments.commentsapi.domain.Message;
import es.uv.dbcds.comments.commentsapi.service.MessageService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * MensajeApi
 */

@RestController
@RequestMapping("api/")
public class MessageController {

    @Autowired
    private MessageService mensajeService;

    public MessageController(MessageService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping("mensajes")
    public List<Message> getAll() {
        return mensajeService.getMessage();
    }

    @GetMapping("mensaje/{id}")
    public ResponseEntity<Optional<Message>> getMensajeById(@PathVariable("id") int id) {
        Optional<Message> m = mensajeService.getMessageById(id);
        
        if (!m.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping("mensaje")
    public ResponseEntity<Message> addMensaje(@RequestBody Message message) {
        mensajeService.addMensaje(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("mensaje/{id}")
    public ResponseEntity<Message> deleteMensaje(@PathVariable("id") int idMensaje) {
        mensajeService.deleteMensaje(idMensaje);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}