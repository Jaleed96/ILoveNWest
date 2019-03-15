package comp3717.bcit.ca.ilovenwest;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    // Instance Members
    private String email;
    private String address2;
    private String Address;
    private String city;
    private String prov;
    private String pcode;
    private String fax;
    private String phone;
    private String Name;
    private String Descriptn;
    private long id;
    private int category;
    private String company;
    private String website;
    private int social_networks;
    private String summary;
    private String catname;
    private double X;
    private double Y;

    public Event(){

    }

    protected Event(Parcel in) {
        email = in.readString();
        address2 = in.readString();
        Address = in.readString();
        city = in.readString();
        prov = in.readString();
        pcode = in.readString();
        fax = in.readString();
        phone = in.readString();
        Name = in.readString();
        Descriptn = in.readString();
        id = in.readLong();
        category = in.readInt();
        company = in.readString();
        website = in.readString();
        social_networks = in.readInt();
        summary = in.readString();
        catname = in.readString();
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

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescriptn() {
        return Descriptn;
    }

    public void setDescriptn(String descriptn) {
        Descriptn = descriptn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public int getSocial_networks() {
        return social_networks;
    }

    public void setSocial_networks(int social_networks) {
        this.social_networks = social_networks;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
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
        dest.writeString(address2);
        dest.writeString(Address);
        dest.writeString(city);
        dest.writeString(prov);
        dest.writeString(pcode);
        dest.writeString(fax);
        dest.writeString(phone);
        dest.writeString(Name);
        dest.writeString(Descriptn);
        dest.writeLong(id);
        dest.writeInt(category);
        dest.writeString(company);
        dest.writeString(website);
        dest.writeInt(social_networks);
        dest.writeString(summary);
        dest.writeString(catname);
        dest.writeDouble(X);
        dest.writeDouble(Y);
    }
}
