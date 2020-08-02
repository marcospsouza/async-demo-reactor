package mono.demo.messages

import groovy.time.TimeCategory
import groovy.time.TimeDuration
import org.springframework.scheduling.annotation.Async
import reactor.core.publisher.Mono

class MessageHandler {

    private MessageProcessor messageProcessor

    MessageHandler(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor
    }

    @Async("processMessage")
    void processMessagesAsync(String message, Date startTime) {
        println Thread.currentThread().getName() + " message" + message
        Mono<String> processedMessage = messageProcessor.processAsync(message)
        processedMessage
                .name("messages")
                .metrics()
                .subscribe({ String result -> emitMessage(result)})

        printExecutionTime(startTime, "Asynchronous processing took")
    }

    void processMessagesSync(String message) {
        String result = messageProcessor.process(message)
        emitMessage(result)
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
