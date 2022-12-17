package ru.romeo;

import ru.romeo.work.ParseData;
import ru.romeo.work.Work;
import ru.romeo.work.Worker;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Settings things up...");

        File dataF = new File("InputData/Bsjadqwpurbvjfqs_data.txt");
        File rafF = new File("InputData/Bsjadqwpurbvjfqs_raf.txt");
        String[] rafS = ParseData.getReader(rafF).readLine().split(",");
        final int SIZE = rafS.length;

        Worker worker = Worker.getInstance();
        worker.create();
        Thread[] threads = new Thread[SIZE];
        Work[] works = new Work[SIZE];
        //for (int i = 0; i<SIZE; i++) {
        String out;
        BufferedReader reader = ParseData.getReader(dataF);
        int count = 0;

        System.out.println("Done!\nStarting threads...");
        while ((out=reader.readLine())!=null) {
            works[count] = new Work(out);
            threads[count] = new Thread(works[count]);
            count++;
        }
        for (Thread t: threads) {
            t.start();
        }

        System.out.println("Done!\nPlease wait for the threads to finish their work\nIt shouldn't take longer than 40-60 seconds");
        while (worker.getState()) {
            try {
                Thread.currentThread().join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("I'm all done.\n"+
                "       .,,.                                                                   \n" +
                "       ,KMKkkkkkkkklll. ..                             ..       .,,,.         \n" +
                "    .lkKKl,,,,,,,lkkKMKkKkll. ..                 ..,l,lKKkkkkkkkKMMKl.        \n" +
                "   .lKKl.           .,,lkkKMKkKk,. ,l.  ,l. ,l. .kKKKkkl,,,,,,,,,lkKMKkl.     \n" +
                " .,kMk.   .,,,,,.         .,,lkKMKkKMKkkKMklKMKkKKl,.       .,,,.  .,kMKl.    \n" +
                " .kMk.    .,lkKMKkkl.        ...,lKMMk,llkMMklKKl.  .... .lkKKl,.    .kMl     \n" +
                " .kMl         .,,lKMKkl.  .. lKl. .ll.   .ll. ..  ,lkKKklKMKl.        lmk.    \n" +
                ".kMk.             .lKMMKllKKkKKKl                .kMKKMMMMMl..        lmk.    \n" +
                " ,KK,              .KMKKKKklkKl..                 ,l..,lKKKKKx,l,    ,KKl.    \n" +
                ".lKK,          ..,,lMK,....  ..                         ..lKkKMMl .. lMKl.    \n" +
                " lMl         ..lKKKKMK,                                   .. lKKKlkl .kMk.    \n" +
                ".kMl         lKKKKMk,.                                       ...lKMK, lMk.    \n" +
                " lMk.      ,lkMk.,l.                                             ,lkl,KK,     \n" +
                " ,KMl     .kkll,                     .,.                ..         ..,Kk,.    \n" +
                " .kMk.    lMl      .lkkkkkkkl,,.    ,KMl                lk,     .lkkkkKMMK,   \n" +
                "  ,KMl    ,l.     .lklkMk,,,lKMKkl,lKMMl                lMKl,,lkkkl,,kMMMMk.  \n" +
                "   lMK.               lMl    .,lkkKMKl,.                .lKMMKkl.   .kMKKMK,  \n" +
                "   ,KMKl.             lMk.       .kMl                     ,KMk.  .,lKKl.,KMk. \n" +
                " .,lKKkl.             ,KMKl,,,,,lKKl.      .lkkkkkkkkkl.   ,KMKkkKKkl.  lMK,  \n" +
                " ,KMKk,.               .lkkkkkkkkl.        lMMMMMMMMMMMl    .,,,,,.     ,KMk. \n" +
                " .lKMMK,                                   .kMMMMMMMMMMl                 lMMl \n" +
                "  .lkKMk..l,                .ll,.           .lKMMMMMMkl.       .,ll.     lMK. \n" +
                " .,,lKKl.lMKkl.            ,KK,               .,lKMKl.           ,KK,    lMK, \n" +
                " .lKMMk,..,,,,.            lMl                  .kMl              lMl   ,KMMK,\n" +
                "   ,KMMK.   ,Kl            lMl          .,,,,,lkKMMKkl,,.         lMl   lMKkl.\n" +
                "  .kMkl.   ,KK,            lMl          .kMMMMKKMMMMKKMMKl.      ,KMl   lMKl. \n" +
                "  .lkKKkkl.lMKkkl.         ,KKl.         .lkKMkkMKKMkkMk,.     .lKMk.   .kMMl \n" +
                "     .lKMMl.,,,,. ..        ,KMKkl.         .lKKl..lKKl.      .kKkl.   ,kKMK, \n" +
                "      .kMMKk,    .kl.,,.     .,,lkl.   ..     ..    ..  ..   .ll.   .,,kMMk.  \n" +
                "     .lKMMMKl,,,.lMKKKl.               lKl.           .lKl          lMKKMK,   \n" +
                "    .kMMKkkkkkKMl.lkk,                 .kMKl,.     .,lKKl.       .lkKMl.,.    \n" +
                "   .lKMMk.    lMKkKMMKk.,,              .lkKMKkkkkkKMKl.      ,k,lKKMK,       \n" +
                "     ,KKl. .. .,,,,kMKl.lK, .,,.           .,,,,,,,,,.   ,;,lkKMKk,.,.        \n" +
                "   .lKMk. .kl.l,   .,.  lMKkKKKklkk,.,,,,.             .,kMMKKMk,.            \n" +
                "   .lKMK,.kMKkk,        ,kl,,.lMKlkKKKkKMK,.,,lllllkkkkkKKll,lMl              \n" +
                "   .lKMk..,,,.                .,. lKl. .ll.lKKMKkl,lKKl...  .kMKl.            \n" +
                "  .lKMk.                   ,l. .. ..       ...l,    ..     ,KMKl,.            \n" +
                "  ,KMKl.  ..               lMklKl                          lMMk.              \n" +
                " .lKMkl.  lk.,l.  .lx,     ,kMKl.                                             \n" +
                " .lKk.    lMKKMl lKKl                                                         ");
        System.out.println(worker.getOutput());
        System.out.println(Arrays.toString(worker.getNums().toArray()));
        BufferedWriter writer = new BufferedWriter(new FileWriter("Result/result_data.txt"));
        writer.write(String.valueOf(worker.getOutput()));
        writer.close();
    }
}
