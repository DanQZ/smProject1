public enum Timeslot {
    MORNING(10, 3, 0),
    AFTERNOON(14, 0, 0),
    EVENING(18, 3, 0);

    private int hour;
    private int minuteFirstDigit;
    private int minuteSecondDigit;

    Timeslot(int hour, int minuteFirstDigit, int minuteSecondDigit) {
        this.hour = hour;
        this.minuteFirstDigit = minuteFirstDigit;
        this.minuteSecondDigit = minuteSecondDigit;
    }

    public int getHour() {
        return hour;
    }

    public int getMinuteFirstDigit() {
        return minuteFirstDigit;
    }

    public int getMinuteSecondDigit(){ return minuteSecondDigit; }
}

