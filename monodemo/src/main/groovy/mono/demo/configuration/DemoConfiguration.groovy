package mono.demo.configuration

import groovy.transform.CompileStatic
import mono.demo.messages.MessageHandlerController
import mono.demo.messages.MessageProcessor
import org.springframework.context.annotation.Bean

@CompileStatic
class DemoConfiguration {

    @Bean
    MessageProcessor messageProcessor() {
        new MessageProcessor()
    }

    @Bean
    MessageHandlerController messageHandlerController(MessageProcessor messageProcessor) {
        new MessageHandlerController(messageProcessor)
    }
}
