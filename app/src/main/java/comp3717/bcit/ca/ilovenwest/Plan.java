package comp3717.bcit.ca.ilovenwest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Plan  implements Parcelable {
    private String name;
    private Date date;
    private ArrayList<Event> events;
    private String dbKey;
    private long dateLong;

    public Plan(String name, Date date, ArrayList<Event> events) {
        this.name = name.isEmpty() ? "New Plan": name;
        this.date = date;
        this.events = events;
        this.dateLong = date.getTime();
    }

    public Plan(String name, Date date) {
        this.name = name.isEmpty() ? "New Plan": name;
        this.date = date;
        this.events = new ArrayList<Event>();
        this.dateLong = date.getTime();
    }

    public Plan(String name, Date date, String dbKey){
        this.name = name.isEmpty() ? "New Plan": name;
        this.date = date;
        this.events = new ArrayList<Event>();
        this.dbKey = dbKey;
        this.dateLong = date.getTime();
    }

    public Plan() {
        this.name = "New Plan";
        this.date = new Date();
        this.events = new ArrayList<Event>();
        this.dateLong = date.getTime();
    }

    protected Plan(Parcel in) {
        name = in.readString();
        date = new Date(in.readString());
        dbKey = in.readString();
        events = in.createTypedArrayList(Event.CREATOR);
        dateLong = in.readLong();
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

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

    public String getName(){
        return name;
    }
    public long getLong(){
        return dateLong;
    }
    public void setLong(long date){
        this.dateLong = date;
    }

    public void setKey(String key){
        this.dbKey = key;
    }
    public String getKey(){
        return dbKey;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date.toString());
        dest.writeString(dbKey);
        dest.writeTypedList(events);
        dest.writeLong(dateLong);
    }
}
