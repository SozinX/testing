package org.sozinx.model;

public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private final String registration;
    private Role role;

    public User(long id, String name, String email, String password, String registration, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistration() {
        return registration;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
