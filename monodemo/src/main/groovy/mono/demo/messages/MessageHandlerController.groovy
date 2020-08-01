package mono.demo.messages

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.*

@RestController
@CompileStatic
class MessageHandlerController {

    private MessageProcessor messageProcessor

    MessageHandlerController(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor
    }

    @PostMapping("/messages")
    void receiveMessages() {
        println("Messages received\n")
        //messageProcessor.processMessagesSync()
        println(">>>>>>>>>>>>>>><<<<<<<<<<<<<<<\n")
        messageProcessor.processMessagesAsync()
    }
}

