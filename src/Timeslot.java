public enum Timeslot {
    MORNING(8, 0),     // Associated with 8:00 AM
    AFTERNOON(12, 0),  // Associated with 12:00 PM
    EVENING(18, 0);    // Associated with 6:00 PM

    private int hour;
    private int minute;

    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}

