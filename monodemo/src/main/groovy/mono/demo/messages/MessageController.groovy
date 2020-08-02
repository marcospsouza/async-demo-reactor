package mono.demo.messages

import groovy.time.TimeCategory
import groovy.time.TimeDuration
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.*

@RestController
@CompileStatic
class MessageController {

    private MessageHandler messageHandler

    private ArrayList<String> messagesList = new ArrayList<>()

    MessageController(MessageHandler messageHandler) {
        this.messageHandler = messageHandler
    }

    @PostMapping("/messagesSync")
    void receiveMessages(@RequestParam Integer numberOfMessages) {
        for (i in 1..numberOfMessages) messagesList.add(i.toString())

        println("Messages received\n")

        Date startTime = new Date()
        messagesList.forEach({ String message ->
            messageHandler.processMessagesSync(message)
        })
        //printExecutionTime(startTime, "Synchronous processing took")
    }

    @PostMapping("messagesAsync")
    void receiveMessagesAsync(@RequestParam Integer numberOfMessages) {
        for (i in 1..numberOfMessages) messagesList.add(i.toString())

        println("Messages received\n")

        Date startTime = new Date()
        messagesList.forEach({ String message ->
            messageHandler.processMessagesAsync(message, startTime)
        })
    }

    private static void printExecutionTime(Date startTime, String message) {
        Date endTime = new Date()
        TimeDuration duration = TimeCategory.minus(endTime, startTime)
        println "${message} ${duration}\n"
    }
}

