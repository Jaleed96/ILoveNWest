package comp3717.bcit.ca.ilovenwest;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    // Instance Members
    private String email;
    private String address;
    private String city;
    private String prov;
    private String pcode;
    private String fax;
    private String phone;
    private String name;
    private String category;
    private String description;
    private String company;
    private String website;
    private String summary;
    private double X;
    private double Y;

    public Event(){
    }

    protected Event(Parcel in) {
        email = in.readString();
        address = in.readString();
        city = in.readString();
        prov = in.readString();
        pcode = in.readString();
        fax = in.readString();
        phone = in.readString();
        name = in.readString();
        category = in.readString();
        description = in.readString();
        company = in.readString();
        website = in.readString();
        summary = in.readString();
        X = in.readDouble();
        Y = in.readDouble();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    //Getter and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(prov);
        dest.writeString(pcode);
        dest.writeString(fax);
        dest.writeString(phone);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeString(company);
        dest.writeString(website);
        dest.writeString(summary);
        dest.writeDouble(X);
        dest.writeDouble(Y);
    }
}
