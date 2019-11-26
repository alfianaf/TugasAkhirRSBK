package jdulal.com.np.androidshopapp;

public class User {
    String firstname, lastname, email, contactno, password;

    public User() {
    }

    public User(String firstname, String lastname,  String contactno, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactno = contactno;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
