public class Event implements Comparable<Event>{
    private Date date;
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;

    @Override
    public int compareTo(Event event){
        if(this.date.compareTo(event.date) > 0){
            return 1;
        }
        if(this.date.compareTo(event.date) < 0){
            return -1;
        }
        return 0;
    }

    public boolean equals(Event event){
        if(this.compareTo(event) == 0){
            return true;
        }
        return false;
    }

    public String toString(){
        String output = "";

        output += "[Event Date: how the fuck do I get the date string]";
        output += "[Start: ]";
        output += "[End: ]";
        output += "[@: ]";
        output += "[Contact: ]";
        return output;
    }
    //[Event Date: 10/21/2023] [Start: 2:00pm] [End: 3:00pm] @HLL114 (Hill Center, Busch) [Contact: Computer Science, cs@rutgers.edu]

}
