package es.uv.dbcds.comments.commentsapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.uv.dbcds.comments.commentsapi.domain.Comment;
import es.uv.dbcds.comments.commentsapi.domain.Message;

/**
 * MessageService
 */

@Service
public class MessageService {

    private final List<Message> messages = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    public MessageService() {
        
        comments.add(new Comment(1,"Name 1","email@gmail.com","Text Comments"));

        messages.add(new Message(1, "Message Titulo 1", "Cuerpo 1", comments));
        messages.add(new Message(1, "Message Titulo 1", "Cuerpo 1", new ArrayList<Comment>()));
        messages.add(new Message(2, "Message Titulo 2", "Cuerpo 2", new ArrayList<Comment>()));
        messages.add(new Message(3, "Message Titulo 3", "Cuerpo 3", new ArrayList<Comment>()));
        messages.add(new Message(4, "Message Titulo 4", "Cuerpo 4", new ArrayList<Comment>()));
        
    }

    public List<Message> getMessage() {
        return messages;
    }

    public Optional<Message> getMessageById(int id) {
        return this.messages.stream().filter(m -> m.getId() == id).findFirst();
    }

    public Optional<Comment> getCommentById(int idMessage, int idComments) {
        return this.messages.stream().filter(m -> m.getId() == idMessage).findFirst().get().getComments().stream()
                .filter(c -> c.getId() == idComments).findAny();
    }

    public void addMensaje(Message mensaje) {
        messages.add(mensaje);
    }

    public void addComment(int id, Comment comment) {
        getMessageById(id).get().getComments().add(comment);
    }

    public void updateMensaje(Message mensaje) {
        Optional<Message> mensajeUpdate = getMessageById(mensaje.getId());
        if (mensajeUpdate.isPresent()) {
            getMessageById(mensaje.getId()).get().setBody(mensaje.getBody());
        }
    }

    public void updateComentario(int idMessage, Comment comments) {
        getCommentById(idMessage, comments.getId()).get().setText(comments.getText());
    }

    public void deleteMensaje(int id) {
        Optional<Message> mensaje = messages.stream().filter(r -> r.getId() == id).findFirst();
        if (mensaje.isPresent()) {
            messages.remove(mensaje.get());
        }
    }

    public void deleteCommnets(int idMessage, int idComments) {
        Optional<Comment> comentario = getCommentById(idMessage, idComments);
        getMessageById(idMessage).get().getComments().remove(comentario.get());
    }

}