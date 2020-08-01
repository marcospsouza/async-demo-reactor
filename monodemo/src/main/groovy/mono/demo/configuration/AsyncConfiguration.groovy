package mono.demo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import java.util.concurrent.Executor

class AsyncConfiguration {
    int corePoolSize = 4
    int maxPoolSize = 8
    int queueCapacity = 50
    String threadNamePrefix = 'message-thread-'

    @Bean
    Executor processMessage() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor()
        executor.setCorePoolSize(corePoolSize)
        executor.setMaxPoolSize(maxPoolSize)
        executor.setQueueCapacity(queueCapacity)
        executor.setThreadNamePrefix(threadNamePrefix)
        executor.initialize()
        executor
    }
}

