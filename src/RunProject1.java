public class RunProject1 {
    public static void main(String[] args) {
        System.out.println("asfdasdfasd");
        Department newDep = Department.CS;
        if(newDep == Department.CS){
            System.out.println("this shit is working!!");
        }
        else{
            System.out.println("this shit is not working!!");
        }

        Location newLocation = Location.AB2225;
        System.out.println("campus: " + newLocation.getCampus() + ", building: " + newLocation.getBuildingName() + ", room: " + newLocation.getRoomNumber());
        new EventOrganizer().run();
    }
}
