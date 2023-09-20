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

    public Date(){};

    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static void main(String[] args){
        Date test = new Date(2000, 4, 7);
        System.out.println("Is this date valid: " + test.isValid());
    }

    public boolean isValid(){
        if(this.month == 1 || this.month == 3 ||
            this.month == 5 || this.month == 7 ||
            this.month == 8 || this.month == 10 ||
            this.month == 12){
            if(this.day <= JMMJAOD && this.day > 0){
                return true;
            }
            else{
                return false;
            }

        }
        else if(this.month == 4 || this.month == 6 ||
            this.month == 9 || this.month == 11){
            if(this.day <= AJSN && this.day > 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(this.month == 2){
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
     * @param year
     * @return True if given year is a leap year, false otherwise.
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
}
