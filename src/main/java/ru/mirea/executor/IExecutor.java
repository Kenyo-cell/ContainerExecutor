package ru.mirea.executor;

import java.io.File;
import java.io.IOException;

public interface IExecutor {
    byte[] execute(File source, File input) throws InterruptedException, IOException;
    File writeResult(byte[] outputData);
}
