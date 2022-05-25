package ru.mirea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mirea.config.YAMLConfig;
import ru.mirea.controller.IShellController;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private IShellController shellController;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);

        application.run(args);
    }

    public void run(String[] args) throws InterruptedException {
        new Thread(shellController).start();
    }
}
