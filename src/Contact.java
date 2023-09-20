/**
 * Contact object class used to construct a contact object.
 * @Kimberly Donnarumma
 */
public class Contact {
    private Department department;
    private String email;

    /**
     * Default constructor.
     */
    public Contact(){}

    /**
     * Parameterized constructor with 2 parameters.
     * @param department
     * @param email
     */
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    public Department getDepartment(){
        return department;
    }

    /**
     * Checks to see if the contact is valid.
     * @return true if the department name and email are valid, false otherwise.
     */
    public boolean isValid(){
        if(this.department.equals(Department.CS) ||
            this.department.equals(Department.EE) ||
            this.department.equals(Department.ITI) ||
            this.department.equals(Department.MATH) ||
            this.department.equals(Department.BAIT)){

            if(this.email.equals("@rutgers.edu")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        String contactString = "";
        contactString += department + email;
        return contactString;
    }
}
