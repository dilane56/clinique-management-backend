package org.kfokam48.cliniquemanagementbackend.mapper;

import org.kfokam48.cliniquemanagementbackend.dto.MessageDTO;
import org.kfokam48.cliniquemanagementbackend.dto.MessageResponseDTO;
import org.kfokam48.cliniquemanagementbackend.model.Message;
import org.kfokam48.cliniquemanagementbackend.repository.UtilisateurRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageMapper {
    private final UtilisateurRepository utilisateurRepository;

    public MessageMapper(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public MessageResponseDTO messageToMessageResponseDTO (Message message){
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setSenderUsername(message.getSender().getUsername());
        messageResponseDTO.setReceiverUsername(message.getReceiver().getUsername());
        messageResponseDTO.setContent(message.getContent());
        messageResponseDTO.setDateEnvoi(message.getDateEnvoi());
        return messageResponseDTO;
    }

   public Message messageDToToMessage(MessageDTO messageDTO){
        Message message = new Message();
        message.setSender(utilisateurRepository.findByUsername(messageDTO.getSenderUsername()).orElseThrow(() -> new RuntimeException("Sender not found")));
        message.setReceiver(utilisateurRepository.findByUsername(messageDTO.getReceiverUsername()).orElseThrow(() -> new RuntimeException("Receiver not found")));
        message.setContent(messageDTO.getContent());
        return message;
   }

   public List<MessageResponseDTO> messageListToMessageResponseDTOList(List<Message> messages) {
        return messages.stream()
                .map(this::messageToMessageResponseDTO)
                .toList();
    }

    public List<Message> messageDTOListToMessageList(List<MessageDTO> messageDTOs) {
        return messageDTOs.stream()
                .map(this::messageDToToMessage)
                .toList();
    }
}
