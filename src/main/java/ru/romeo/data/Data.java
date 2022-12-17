package ru.romeo.data;

public class Data {
    private final short id;
    private final long number;

    public Data (short i, long nu) {
        id=i;
        number=nu;
    }

    public long getNumber() {
        return number;
    }

    public short getId() {
        return id;
    }
}
