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
        this.month = CALENDAR.get(Calendar.MONTH);
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
     * Testbed.
     * @param args
     */
    public static void main(String[] args){
        Date test = new Date(2000, 4, 7);
        System.out.println("Is this date valid: " + test.isValid());
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

    public int pastOrTooFar(){
        Date currentDate = new Date();
        int pastOrFuture = this.compareTo(currentDate);

        if(pastOrFuture == -1){
            return -1;
        }
        else {
            int month = currentDate.month;
            int year = currentDate.year;
            month += 6;
            if(month > 12){
                year++;
                month -= 12;
            }
            if(this.year > year){
                return 1;
            }
            if(this.month > month){
                return 1;
            }
            else if(this.month == month){
                if(this.day > currentDate.day){
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
