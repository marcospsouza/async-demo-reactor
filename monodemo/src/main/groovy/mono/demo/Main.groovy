package mono.demo

import mono.demo.configuration.AsyncConfiguration
import mono.demo.configuration.DemoConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.jms.annotation.EnableJms
import org.springframework.scheduling.annotation.EnableAsync

import static org.springframework.boot.SpringApplication.run

@EnableAutoConfiguration
@Configuration
@EnableJms
@EnableAsync
@Import([
        AsyncConfiguration,
        DemoConfiguration
])
class Main {
    static void main(String[] args) {
        run Main, args
    }
}
