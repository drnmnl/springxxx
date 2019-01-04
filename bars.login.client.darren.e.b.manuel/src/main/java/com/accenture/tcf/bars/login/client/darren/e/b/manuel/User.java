package com.accenture.tcf.bars.login.client.darren.e.b.manuel;

public class User {
    private int userId;
    private String userName;
    private String role;
    private String firstName;
    private String lastName;
    private String password;

    public User(String userName, String role, String firstName, String lastName, String password) {
        this.userName = userName;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User() {   //REQUIRED FOR JPA!
    }

//    @ManyToMany
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    public Set<Role> getRoles() {
//        return roles;
//    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

