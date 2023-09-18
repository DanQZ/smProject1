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
}
