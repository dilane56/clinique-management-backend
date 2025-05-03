package org.kfokam48.cliniquemanagementbackend.controlleur;
import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.MessageDTO;
import org.kfokam48.cliniquemanagementbackend.dto.MessageResponseDTO;
import org.kfokam48.cliniquemanagementbackend.service.impl.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Validated
public class MessageController {

    private final MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }


    @GetMapping("/sender/{senderId}/receiver/{receiverId}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesBySenderAndReceiver(
            @PathVariable Long senderId,
            @PathVariable Long receiverId) {
        List<MessageResponseDTO> messages = messageService.findBySenderAndReceiver(senderId, receiverId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/receiver/{receiverUsername}/sender/{senderUsername}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesByReceiverAndSender(
            @PathVariable String receiverUsername,
            @PathVariable String senderUsername) {
        List<MessageResponseDTO> messages = messageService.findByReceiverAndSender(receiverUsername, senderUsername);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/sender/{senderId}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesBySender(@PathVariable Long senderId) {
        List<MessageResponseDTO> messages = messageService.findBySender(senderId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesByReceiver(@PathVariable Long receiverId) {
        List<MessageResponseDTO> messages = messageService.findByReceiver(receiverId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDTO>> getAllMessages() {
        List<MessageResponseDTO> messages = messageService.findAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody @Valid MessageDTO messageDTO) {
        MessageResponseDTO response = messageService.sendMessage(messageDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<MessageResponseDTO> updateMessage(
            @PathVariable Long messageId,
            @RequestBody @Valid MessageDTO messageDTO) {
        MessageResponseDTO updatedMessage = messageService.updateMessage(messageId, messageDTO);
        return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMessage(@RequestParam Long messageId, @RequestParam Long senderId) {

        return messageService.deleteMessage(messageId, senderId);
    }
}
