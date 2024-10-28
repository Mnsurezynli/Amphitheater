package org.example.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Reserve> reserveList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Reserve> getReserveList() {
        return reserveList;
    }

    public void setReserveList(List<Reserve> reserveList) {
        this.reserveList = reserveList;
    }
}
