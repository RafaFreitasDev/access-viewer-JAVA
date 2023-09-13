package br.com.rafael.accessviewerjava.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
//nome da tabela
@Table(name="users")
public class User implements UserDetails {
    @Id                      //GenerationType.(tem várias opções)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 92, nullable = false)
    private String name;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 62, nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private UserType type;

    @Column(columnDefinition = "DECIMAL")
    @ColumnDefault("0")
    private float balance;

    public User() {
    }

    public User(String name, String cpf, String email, String password, UserType type) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.type = type;
    }



    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.type==UserType.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                                              new SimpleGrantedAuthority("ROLE_SELLER"),
                                              new SimpleGrantedAuthority("ROLE_COMMON"));
        else if(this.type==UserType.COMMON) return List.of(new SimpleGrantedAuthority("ROLE_COMMON"));
        else return List.of(new SimpleGrantedAuthority("ROLE_SELLER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    //sobrescrever método toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                '}';
    }
}
