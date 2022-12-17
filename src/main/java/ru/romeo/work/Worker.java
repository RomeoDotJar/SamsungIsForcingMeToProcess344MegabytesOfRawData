package ru.romeo.work;

import ru.romeo.data.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Worker {
    private static Worker inst;
    public static Worker getInstance() {
        if (inst == null) {
            inst = new Worker();
        }
        return inst;
    } private Worker() {
        nums = new ArrayList<Long>();
        queue = new ArrayDeque<Data>();
        doWork = false;

        timer = new Timer();
        schedule = new Schedule();
    }


    private final ArrayDeque<Data> queue;
    private ArrayList<Long> nums;
    private long out;
    private boolean doWork;
    private Timer timer; // for timed work optimization
    private Schedule schedule;
    private final int INTERVALIDLE = 1000;
    private final int INTERVALWORK = 25;
    private int tickNew = INTERVALIDLE;
    private int tickOld = 0;
    private int emptyQueueCount = 0;

    private class Schedule extends TimerTask {
        @Override
        public void run() {
            if (doWork) {
                processQueue();
            }
            updateQueueInfo(queue.size());
            if (tickNew == INTERVALIDLE) {
                if (++emptyQueueCount > 20) {
                    stop();
                }
            }
        }
    }

    private void processQueue() {
        if (queue.size()>0) {
            Data data = queue.poll();
            if (data.getNumber()!=-1)
                nums.add(data.getNumber());
            //System.out.println("Found data "+data.getNumber()+" with id "+data.getId());
        }
        //else System.err.println("non");
    }

    private void updateQueueInfo(int queueSize) {
        tickNew = queueSize==0 ? INTERVALIDLE:INTERVALWORK;
        if (tickNew != tickOld)
            restart();
        tickOld = tickNew;
    }

    private void restart() {
        timer.cancel();
        timer = new Timer();
        schedule = new Schedule();
        timer.scheduleAtFixedRate(schedule, 0, tickNew);
        emptyQueueCount=0;
    }

    private void start() {
        timer.scheduleAtFixedRate(schedule, 0, tickNew);
        doWork = true;
    }

    private void stop() {
        timer.cancel();
        doWork = false;
        int count=0;
        for (long num:nums) {
            out+=num;
            count++;
        }
        out/=count;
    }

    private void addToQueue(Data data) {
        synchronized (queue) {
            queue.add(data);
        }
    }

    public void create() {
        start();
    }

    public void destroy() {
        stop();
    }

    public void addToWork(Data data) {
        addToQueue(data);
    }

    public boolean getState() {
        return doWork;
    }

    public long getOutput() {
        return out;
    }

    public ArrayList<Long> getNums() {
        return nums;
    }
}
