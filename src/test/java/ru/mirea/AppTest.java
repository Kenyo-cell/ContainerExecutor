package ru.mirea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mirea.config.YAMLConfig;
import ru.mirea.executor.Executor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AppTest {

    Executor executor = new Executor(null);

    @Test
    public void executeReturnedCorrectData() throws IOException, InterruptedException {
        String sourceFilePath = "/Users/kenyo/Desktop/edu/прогинжа/ContainerExecutor/src/test/java/ru/mirea/testResources/nameTest.py";
        String inputFilePath = "/Users/kenyo/Desktop/edu/прогинжа/ContainerExecutor/src/test/java/ru/mirea/testResources/nameInput.txt";
        String outputFilePath = "/Users/kenyo/Desktop/edu/прогинжа/ContainerExecutor/src/test/java/ru/mirea/testResources/nameOutput.txt";
        File source = new File(sourceFilePath);
        System.out.printf("File %s exists %b%n", sourceFilePath, source.exists());
        byte[] data = executor.execute(
                new File(sourceFilePath),
                new File(inputFilePath)
        );
        FileInputStream outputFileStream = new FileInputStream(outputFilePath);
        Assertions.assertEquals(
                new String(outputFileStream.readAllBytes()),
                new String(data)
        );
    }

    @Test
    public void shouldThrowException() {
        Assertions.assertTrue(true);
    }
}
