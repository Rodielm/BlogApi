package es.uv.dbcds.comments.commentsapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

import es.uv.dbcds.comments.commentsapi.domain.Comment;
import es.uv.dbcds.comments.commentsapi.domain.Message;


/**
 * MessageService
 */

@Service
public class MessageService {

    private final AtomicInteger idSequence = new AtomicInteger();
    private final HashMap<Integer, Integer> idSequenceComment = new HashMap<>();
    private final List<Message> messages = new ArrayList<>();

    // private final MessageRepository repository;

    
    public MessageService() {
        messages.add(new Message(idSequence.incrementAndGet(), "My First Message", "Content of the first Message"));
        messages.add(new Message(idSequence.incrementAndGet(), "My second Message", "Content 2nd Message"));
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Message getMessageById(int id) {
        return messages.stream().filter(m -> m.getId() == id).findFirst()
                .orElseThrow(() -> new MessageNotFoundException("No message found with id " + id));
    }

    public Message addLiketoMessage(int id){
        Message m = this.getMessageById(id);
        int cont = m.getLike();
        m.setLike(++cont);
        return m;
    }


    public Comment addLiketoComment(int id, int idComment){
        Comment comment = this.getMessageById(id).getComments().stream()
        .filter(c -> c.getId() == idComment).findFirst().orElse(null);
        int cont = comment.getLike();
        comment.setLike(++cont);
        return comment;
    }

    public Message addMessage(Message message) {
        message.setId(idSequence.incrementAndGet());
        messages.add(message);
        return message;
    }

    public Message updateMessage(int id, Message message) {
        Message oldMessage = getMessageById(id);
        oldMessage.setTitle(message.getTitle());
        oldMessage.setBody(message.getBody());
        oldMessage.setComments(message.getComments());
        return oldMessage;
    }

    public void deleteMessage(int id) {
        messages.removeIf(m -> m.getId() == id);
    }

    ///////// Comments /////////////////////////

    public Comment addComment(int id, Comment comment) {
        int idComment = CommentIncrement(id);
        comment.setParent(getMessageById(id));
        comment.setId(idComment);
        getMessageById(id).getComments().add(comment);
        return comment;
    }

    // public Optional<Comment> getCommentById(int idMessage, int idComments) {
    // return this.messages.stream().filter(m -> m.getId() ==
    // idMessage).findFirst().get().getComments().stream()
    // .filter(c -> c.getId() == idComments).findAny();
    // }

    public Comment updateComment(int idMessage, int idComment, Comment comments) {
        Comment oldComment = getMessageById(idMessage).getComments().stream().filter(c -> c.getId() == idComment)
                .findAny().orElse(null);
        oldComment.setText(comments.getText());
        return oldComment;
    }

    public void deleteComment(int idMessage, int idComment) {
        getMessageById(idMessage).getComments().removeIf(c -> c.getId() == idComment);

    }

    public int CommentIncrement(Integer idMensaje) {
        int idIncrement = 0;
        if (idSequenceComment.containsKey(idMensaje)) {
            idIncrement = idSequenceComment.get(idMensaje);
            idSequenceComment.put(idMensaje,++idIncrement );
        } else {
            idSequenceComment.put(idMensaje,++idIncrement );
        }
        return idIncrement;
    }

}