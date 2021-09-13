package com.bootcampTeam4.bootcampBankingApp.classes;


import javax.persistence.*;

@Entity
@Table(name = "testuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @Column(name = "email")
//    private String email;
//
    @Column(name = "first_name")
    private String first_name;
//
//    @Column(name = "last_name")
//    private String last_name;
//
//    @Column(name = "address")
//    private String address;
//
//    @Column(name = "phone")
//    private int phone;
//
//    @Column(name = "password")
//    private String password;


//    public User(long id, String email, String first_name, String last_name, String address, int phone, String password) {
//        this.id = id;
//        this.email = email;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.address = address;
//        this.phone = phone;
//        this.password = password;
//    }


    public User(long id, String first_name) {
        this.id = id;
        this.first_name = first_name;
    }

    public User() { }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", first_name='" + first_name + '\'' +
//                ", last_name='" + last_name + '\'' +
//                ", address='" + address + '\'' +
//                ", phone=" + phone +
//                ", password='" + password + '\'' +
//                '}';
//    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFirst_name() {
//        return first_name;
//    }
//
//    public void setFirst_name(String first_name) {
//        this.first_name = first_name;
//    }
//
//    public String getLast_name() {
//        return last_name;
//    }
//
//    public void setLast_name(String last_name) {
//        this.last_name = last_name;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public int getPhone() {
//        return phone;
//    }
//
//    public void setPhone(int phone) {
//        this.phone = phone;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
