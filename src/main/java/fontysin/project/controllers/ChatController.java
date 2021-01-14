package fontysin.project.controllers;

import fontysin.project.entities.model.ChatMessage;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.services.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Controller
public class ChatController {

    private final UserService userService;

    public ChatController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/chat")
    @SendTo("/api/topic/public")
    public ChatMessage handleMessage(ChatMessage message) {
        try{
            var pcn = Integer.parseInt(message.getSender());
            AppUser user = userService.getUserByPcn(pcn);

            if(user == null){
                throw new InternalServerException("Unable to parse PCN");
            }

            message.setSender(user.getName());
            return message;
        }catch(Exception e){
            message.setContent("Could not identify User");
            message.setType(ChatMessage.MessageType.JOIN);
            message.setSender("System");
            return message;
        }
    }

}