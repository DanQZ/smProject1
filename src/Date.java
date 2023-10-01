import java.util.Calendar;
/**
 * Define the abstract data type Date.
 * @KimberlyDonnarumma,
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    public static final int JMMJAOD = 31;
    public static final int AJSN = 30;
    public static final int FNL = 28;
    public static final int FL = 29;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    public Calendar CALENDAR = Calendar.getInstance();

    /**
     * Default constructor.
     */
    public Date(){
        this.year = CALENDAR.get(Calendar.YEAR);
        this.month = CALENDAR.get(Calendar.MONTH) + 1;
        this.day = CALENDAR.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Parameterized constructor with 3 parameters.
     * @param year
     * @param month
     * @param day
     */
    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Testbed main to test the isValid() method.
     * @param args
     */
    public static void main(String[] args){
        testDaysInFeb_nonLeap(); // Test 1
        testDaysInFeb_Leap(); // Test 2
        testMonth_outOfRange31(); // Test 3
        testMonth_outOfRange30(); // Test 4
        testDay_lessThan1(); // Test 5
        testMonthInvalid(); // Test 6
    }

    private static void testDaysInFeb_nonLeap(){
        Date date = new Date(2011, 2, 29);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 1: # of days in Feb in a " +
                "non-leap year is 28.");
        testResult(date, expectedOutput, actualOutput);
        date.day = 0;
        testResult(date, expectedOutput, actualOutput);
    }

    private static void testDaysInFeb_Leap(){
        Date date = new Date(2000, 2, 29);
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 2: # of days in Feb in a " +
                "leap year is 29.");
        testResult(date, expectedOutput, actualOutput);
    }

    private static void testMonth_outOfRange31(){
        Date date = new Date(2000, 1, 31);
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 3: # of days in January, March, May," +
                " July, August, October, and December is 31.");
        testResult(date, expectedOutput, actualOutput);
        date.month = 3;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.month = 5;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.month = 7;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.month = 8;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.month = 10;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.month = 12;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.day = 32;
        expectedOutput = false;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
    }

    private static void testMonth_outOfRange30(){
        Date date = new Date(1000, 4, 30);
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 4: # of days in April, June," +
                " September, and November is 30.");
        testResult(date, expectedOutput, actualOutput);
        date.month = 6;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.month = 9;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.month = 11;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
        date.day = 31;
        expectedOutput = false;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
    }

    private static void testDay_lessThan1(){
        Date date = new Date(2024, 4, 0);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 5: # of days must be greater than 1.");
        testResult(date, expectedOutput, actualOutput);
        date.day = -1;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
    }

    private static void testMonthInvalid(){
        Date date = new Date(2003, 0, 23);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 6: Month most be between 1 and 12 inclusive.");
        testResult(date, expectedOutput, actualOutput);
        date.month = 13;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
    }

    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput){
        System.out.println(date.toString());
        if(expectedOutput == actualOutput){
            System.out.println("PASS");
        }
        else {
            System.out.println("FAIL");
        }
        System.out.println();
    }

    /**
     * Checks if this date is a valid date or not.
     * @return true if date is valid, false otherwise.
     */
    public boolean isValid(){
        if(this.month == JANUARY || this.month == MARCH ||
            this.month == MAY || this.month == JULY ||
            this.month == AUGUST || this.month == OCTOBER ||
            this.month == DECEMBER){
            if(this.day <= JMMJAOD && this.day > 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(this.month == APRIL || this.month == JUNE ||
            this.month == SEPTEMBER || this.month == NOVEMBER){
            if(this.day <= AJSN && this.day > 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(this.month == FEBRUARY){
            if(isLeap(this.year)){
                if(this.day <= FL && this.day > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                if(this.day <= FNL && this.day > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a year is a leap year.
     * @param year
     * @return true if given year is a leap year, false otherwise.
     */
    private boolean isLeap(int year){
        if(year % QUADRENNIAL == 0) {
            if(year % CENTENNIAL == 0){
                if(year % QUARTERCENTENNIAL == 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the date is in the past or if it's too far in the future.
     * @return -1 if it's in the past, 1 if it's over 6 months in the future,
     * 0 if neither.
     */
    public int pastOrTooFar(){
        Date currentDate = new Date();
        int pastOrFuture = this.compareTo(currentDate);

        if(pastOrFuture == -1){
            return -1;
        }
        else {
            int maxMonth = currentDate.month + (currentDate.year * 12);
            maxMonth += 6;

            int eventMonth = this.month + (this.year * 12);
            if(maxMonth < eventMonth){
                return 1;
            }
            else if(maxMonth == eventMonth){
                if(currentDate.day < this.day){
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public int compareTo(Date date){
        if(this.year < date.year){
            return -1;
        }
        else if(this.year > date.year){
            return 1;
        }
        else{
            if(this.month < date.month){
                return -1;
            }
            else if(this.month > date.month){
                return 1;
            }
            else{
                if(this.day < date.day){
                    return -1;
                }
                else if(this.day > date.day){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
    }

    @Override
    public String toString(){
        String dateString = "";

        dateString += month;
        dateString += "/";
        dateString += day;
        dateString += "/";
        dateString += year;

        return dateString;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Date){
            Date date = (Date) obj;
            if(this.year == date.year &&
                    this.month == date.month &&
                    this.day == date.day) {
                return true;
            }
        }
        return false;
    }
}
