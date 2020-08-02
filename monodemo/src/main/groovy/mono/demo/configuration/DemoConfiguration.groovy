package mono.demo.configuration

import groovy.transform.CompileStatic
import groovyjarjarantlr.debug.MessageAdapter
import mono.demo.messages.MessageController
import mono.demo.messages.MessageHandler
import mono.demo.messages.MessageProcessor
import org.springframework.context.annotation.Bean

@CompileStatic
class DemoConfiguration {

    @Bean
    MessageProcessor messageProcessor() {
        new MessageProcessor()
    }

    @Bean
    MessageHandler messageHandler(MessageProcessor messageProcessor) {
        new MessageHandler(messageProcessor)
    }

    @Bean
    MessageController messageHandlerController(MessageHandler messageHandler) {
        new MessageController(messageHandler)
    }
}
