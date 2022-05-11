package ru.mirea.controller.implementation;

import ru.mirea.config.YAMLConfig;
import ru.mirea.controller.IShellController;
import ru.mirea.executor.Executor;
import ru.mirea.transfer.ExecutionContextTransferObject;
import ru.mirea.transfer.ExecutionResultTransferObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

/**
 * Реализация {@link ru.mirea.controller.IShellController IShellController}, основанная на сокетах и низкоуровневом сетевом взаимодействии
 */
public class SocketShellController extends IShellController {
    private YAMLConfig config;
    private ServerSocket serverSocket;

    public SocketShellController(Executor executor, YAMLConfig config) throws IOException {
        super(executor);
        this.config = config;
        serverSocket = new ServerSocket(config.getPort());
    }

    public void receiveConnection() {
        try(
                Socket client = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream())
        ) {

            ExecutionContextTransferObject transferObject = (ExecutionContextTransferObject) ois.readObject();

            File source = new File(config.getWorkdir() + "source");
            File input = new File(config.getWorkdir() + "input");

            Files.write(source.toPath(), transferObject.source());
            Files.write(input.toPath(), transferObject.input());


            byte[] result = executor.execute(source, input);
            ExecutionResultTransferObject resultObject = new ExecutionResultTransferObject(result);

            oos.writeObject(resultObject);
            oos.flush();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            receiveConnection();
        }
    }
}
