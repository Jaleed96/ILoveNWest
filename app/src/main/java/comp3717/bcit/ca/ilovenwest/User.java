package comp3717.bcit.ca.ilovenwest;

public class User {
    private String userId;
    private String email;
    private String password;

    User(String userId, String email, String password){
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getUserId(){
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!User.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final User other = (User) obj;
        if(this.email.equals(other.email) && this.password.equals(other.password)){
            return true;
        }
        else{
            return false;
        }
    }
}
