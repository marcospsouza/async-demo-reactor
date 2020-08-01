package mono.demo.messages

import groovy.time.TimeCategory
import groovy.time.TimeDuration
import org.springframework.scheduling.annotation.Async
import reactor.core.publisher.Mono

class MessageProcessor {

    private ArrayList<String> messagesList = ["first message", "second message", "third message", "fourth message"] as
            ArrayList<String>

    MessageProcessor() {
    }

    void processMessagesAsync() {
        Date startTime = new Date()

        messagesList.forEach({ String message ->
            Mono<String> processedMessage = processAsync(message)
            processedMessage.subscribe({ String result -> emitMessage(result) })
        })

        printExecutionTime(startTime, "Asynchronous processing took")
    }

    void processMessagesSync() {
        Date startTime = new Date()

        messagesList.forEach({ String message ->
            String processedMessage = process(message)
            emitMessage(processedMessage)
        })

        printExecutionTime(startTime, "Synchronous processing took")
    }

    @Async("processMessage")
    private Mono<String> processAsync(String message) {
        Mono.just(process(message))
    }

    private String process(String message) {
        println "Started processing ${message}"
        Thread.sleep(3000)
        println "Finished processing ${message}"
        return "processed " + message
    }

    private static void emitMessage(String message) {
        println "Emitting ${message}\n"
    }

    private static void printExecutionTime(Date startTime, String message) {
        Date endTime = new Date()
        TimeDuration duration = TimeCategory.minus(endTime, startTime)
        println "${message} ${duration}\n"
    }
}
