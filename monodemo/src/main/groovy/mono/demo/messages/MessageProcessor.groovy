package mono.demo.messages

import reactor.core.publisher.Mono

class MessageProcessor {

    Mono<String> processAsync(String message) {
        Mono.just(process(message))
    }

    String process(String message) {
        println "Started processing ${message}\n"
        Random random = new Random()
        Thread.sleep(random.nextInt(100) + 2900)
        return message
    }
}
