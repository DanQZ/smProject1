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

    public String getFullName(){
        return fullName;
    }
}