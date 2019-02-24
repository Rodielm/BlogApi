package es.uv.dbcds.comments.commentsapi.service;

import java.util.ArrayList;
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
    private final AtomicInteger idSequenceComment = new AtomicInteger();
    private final List<Message> messages = new ArrayList<>();

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
        comment.setId(idSequenceComment.incrementAndGet());
        getMessageById(id).getComments().add(comment);
        return comment;
    }

    // public Optional<Comment> getCommentById(int idMessage, int idComments) {
    // return this.messages.stream().filter(m -> m.getId() ==
    // idMessage).findFirst().get().getComments().stream()
    // .filter(c -> c.getId() == idComments).findAny();
    // }

    public Comment updateComment(int idMessage,int idComment ,Comment comments) {
        Comment oldComment = getMessageById(idMessage).getComments().stream().filter(c -> c.getId() == idComment)
                .findAny().orElse(null);
        oldComment.setText(comments.getText());
        return oldComment;
    }

    public void deleteComment(int idMessage, int idComment) {
        getMessageById(idMessage).getComments().removeIf(c -> c.getId() == idComment);

    }

}