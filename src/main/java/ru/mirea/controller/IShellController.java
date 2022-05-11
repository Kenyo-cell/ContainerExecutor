package ru.mirea.controller;

import ru.mirea.executor.Executor;

import java.net.Socket;

/**
 * Основной интерфейс для деларирования поведения связи с внешним ресурсом и процесса запуска файла на исполнение
 * Является абстрактным классом, так как обязательно должен содержать в себе реализацю {@link ru.mirea.executor.Executor Executor}
 */
public abstract class IShellController implements Runnable {
    protected final Executor executor;

    /**
     * @param executor обязательный член класса, который будет отвечать за исполнение процессов
     */
    public IShellController(Executor executor) {
        this.executor = executor;
    }
}
