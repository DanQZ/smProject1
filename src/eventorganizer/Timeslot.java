package eventorganizer;

/**
 * Define the abstract data type Timeslot.
 * @KimberlyDonnarumma
 * @DanielZhang
 */
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

    /**
     * Getter method.
     * @return Hour of the timeslot.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Getter method.
     * @return First digit of the minutes of the timeslot.
     */
    public int getMinuteFirstDigit() {
        return minuteFirstDigit;
    }

    /**
     * Getter method.
     * @return Second digit of the minutes of the timeslot.
     */
    public int getMinuteSecondDigit(){ return minuteSecondDigit; }

    /**
     * Method to return hour in standard time.
     * @return Hour in standard time.
     */
    public int getHourStandard(){
        if(hour > 12){
            return hour - 12;
        }

        return hour;
    }
}

