package ru.mirea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.executor.IExecutor;
import ru.mirea.executor.StandardExecutorImpl;


@Configuration
public class ApplicationConfig {
    @Bean
    public IExecutor standardExecutorImpl() {
        return new StandardExecutorImpl();
    }
}
