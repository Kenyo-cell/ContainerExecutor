package ru.mirea.executor;

import ru.mirea.config.YAMLConfig;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Класс отвечающий за исполнение кода
 */
public class Executor {

    private final YAMLConfig config;

    /**
     * Конструктор с парметром для большей гибкости конфигруации контейнера
     * @param config класс конфигурации
     */
    public Executor(YAMLConfig config) {
        this.config = config;
    }

    /**
     * Основной метод, предназначенный для исполнения кода из-под оболчки контейнера
     * @param source файл с исходным кодом
     * @param input файл с входными данными
     * @return возвращает результат в виде последовательности байт {@code byte[]}
     * @throws InterruptedException выбрасывается в случае возникновения ошибки во время выполнения процесса или в случае принудительного завершения
     * @throws IOException выбрасывается в случае ошибки работы с переданными файлами или с файлами вывода
     */
    public byte[] execute(File source, File input) throws InterruptedException, IOException {
        File output = new File(config.getWorkdir() + "output");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectInput(input);
        processBuilder.redirectErrorStream(true);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.to(output));
        processBuilder.redirectError(ProcessBuilder.Redirect.appendTo(output));
        var commands = new LinkedList<>(config.getEnvironment().getCommands());
        commands.add(source.getAbsolutePath());
        String[] command = commands.toArray(new String[0]);

        processBuilder.command(command);
        Process process = processBuilder.start();
        process.waitFor(30, TimeUnit.SECONDS);
        return Files.lines(output.toPath()).collect(Collectors.joining("\n")).getBytes(StandardCharsets.UTF_8);
    }
}
