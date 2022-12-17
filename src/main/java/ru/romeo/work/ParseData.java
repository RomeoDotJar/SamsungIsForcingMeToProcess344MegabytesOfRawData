package ru.romeo.work;

import ru.romeo.data.Data;

import java.io.*;
import java.util.ArrayList;

public class ParseData {

    public ParseData(File f) {
        ArrayList<Thread> threads = new ArrayList<>();
        String out;

        try (
                BufferedReader bReader = getReader(f);
        ){
            /*while ((out=bReader.readLine())!=null) {
                threads.add(new Thread( new Worker(out) ));
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Thread t: threads) {
            t.start();
        }
    }

    public static BufferedReader getReader(File f) {
        BufferedReader bReader = null;
        try {
            FileReader fReader = new FileReader(f);
            bReader = new BufferedReader(fReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bReader;
    }
}
