package ru.romeo.work;

import com.google.gson.internal.Streams;
import ru.romeo.data.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Work implements Runnable {
    private final String line;
    private final Worker worker;

    public Work(String out) {
        worker=Worker.getInstance();
        line=out;
    }

    @Override
    public void run() {
        final short VALID_ID = 64;
        String[] params = line.split(";")[1].split(",");
        short id = Short.parseShort(params[1].split(":")[1]);
        if (id != VALID_ID) {
            worker.addToWork(new Data(id, -1));
            return;
        }
        long num = Long.parseLong(params[2].split(":")[1].replace("}",""));
        worker.addToWork(new Data(id, num));
    }

}
