public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    public static final int JMMJAOD = 31;
    public static final int AJSN = 30;
    public static final int FNL = 28;
    public static final int FL = 29;

    public boolean isValid(){
        if(this.month == 1 || this.month == 3 ||
            this.month == 5 || this.month == 7 ||
            this.month == 8 || this.month == 10 ||
            this.month == 12){
            if(this.day < JMMJAOD && this.day > 0){
                return true;
            }
            else{
                return false;
            }

        }
        else if(this.month == 4 || this.month == 6 ||
            this.month == 9 || this.month == 11){
            if(this.day < AJSN && this.day > 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(this.month == 2){

        }
    }

    /**
     *
     * @param year
     * @return true If given year is a leap year
     */
    private boolean isLeap(int year){
        if(year % 4 == 0) {

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
}
