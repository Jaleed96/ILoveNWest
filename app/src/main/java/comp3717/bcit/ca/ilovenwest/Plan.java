package comp3717.bcit.ca.ilovenwest;

import java.util.ArrayList;
import java.util.Date;

public class Plan {
    private String name;
    private Date date;
    private ArrayList<Event> events;

    public Plan(Date date, ArrayList<Event> events) {
        this.name = "New Plan";
        this.date = date;
        this.events = events;
    }

    public Plan(Date date) {
        this.name = "New Plan";
        this.date = date;
        this.events = new ArrayList<Event>();
    }

    public Plan() {
        this.name = "New Plan";
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvent(ArrayList<Event> events) {
        this.events = events;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
