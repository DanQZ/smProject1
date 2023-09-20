public enum Timeslot {
    MORNING(10, 3, 0),     // Associated with 8:00 AM
    AFTERNOON(2, 0, 0),  // Associated with 12:00 PM
    EVENING(6, 3, 0);    // Associated with 6:00 PM

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

