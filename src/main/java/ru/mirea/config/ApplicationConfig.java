package ru.mirea.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.controller.IShellController;
import ru.mirea.controller.implementation.SocketShellController;
import ru.mirea.executor.Executor;

import java.io.IOException;


@Configuration
public class ApplicationConfig {
    @Bean
    public Executor homeFolderExecutor(YAMLConfig config) {
        return new Executor(config);
    }

    @Bean
    public IShellController shellController(Executor executor, YAMLConfig config) throws IOException {
        return new SocketShellController(executor, config);
    }
}
