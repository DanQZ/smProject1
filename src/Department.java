/**
 * Enum class to define the abstract data type Department.
 * @
 */
public enum Department {
    CS("Computer Science"),
    ITI("Information Technology and Informatics"),
    EE("Electrical Engineering"),
    MATH("Mathematics"),
    BAIT("Business Analytics and Information Technology");

    private final String fullName;

    Department(String fullName){
        this.fullName = fullName;
    }

    /**
     * Getter method.
     * @return Full name of the department.
     */
    public String getFullName(){
        return fullName;
    }
}