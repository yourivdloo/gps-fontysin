package fontysin.project.controllers;

import fontysin.project.entities.model.ChatMessage;
import fontysin.project.services.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final UserService userService;

    public ChatController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/public")
    public ChatMessage handleMessage(ChatMessage message) {
        message.setSender(userService.getUserByPcn(Util.getPcn()).getName());
        return message;
    }

}