package org.kfokam48.cliniquemanagementbackend.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDTO {

    @NotNull(message = "Sender username cannot be null")
    private String senderUsername;
    @NotNull(message = "Receiver username cannot be null")
    private String receiverUsername;
    private String content;


}
