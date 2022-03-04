package ru.mirea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mirea.config.YAMLConfig;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private YAMLConfig config;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);

        application.run(args);
    }

    public void run(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        System.out.printf("lang: %s%n", config.getLanguage());
        System.out.printf("port: %d%n", config.getPort());
//        Thread.sleep(10000);
    }
}
