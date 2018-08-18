package hut.cwp.annotations;

public enum InitLevel {
    VERY_HIGHT(100),
    HIGHT(200),
    NORMAL(300),
    LOW(400),
    VERY_LOW(500);

    public int getValue() {
        return value;
    }

    private int value;
    InitLevel(int value) {
        this.value = value;
    }
}
