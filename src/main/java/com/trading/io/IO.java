package com.trading.io;


import java.io.FileWriter;
import java.io.IOException;

public class IO {
    public static void writeToFile(String val, String pathToFile, OpenMode openMode) throws IOException {
        if (openMode == OpenMode.READ) throw new IllegalArgumentException("OpenMode cannot be set to: %s".formatted(openMode));

        if (openMode == OpenMode.WRITE) {
            try (FileWriter fw = new FileWriter(pathToFile)) {
                fw.write(val);
            }
        }

        if (openMode == OpenMode.APPEND) {
            try (FileWriter fw = new FileWriter(pathToFile, true)) {
                fw.write(val);
            }
        }
    }
    
    enum OpenMode { READ, APPEND, WRITE}
}
