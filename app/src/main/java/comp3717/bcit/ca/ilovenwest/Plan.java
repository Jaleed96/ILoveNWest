package comp3717.bcit.ca.ilovenwest;

import java.util.Date;

public class Plan {
    Date date;
    Event event;

    public Plan(Date date, Event event){
        this.date = date;
        this.event = event;
    }
    public Plan(){}
    public void setEvent(Event event) {
        this.event = event;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public Event getEvent() {

        return event;
    }

    public Date getDate() {

        return date;
    }
}
