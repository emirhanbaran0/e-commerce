package com.innova.ecommerce.entity.model;

import com.innova.ecommerce.entity.enums.UserRole;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@Entity
@Table(name = "kullanicilar")
@NoArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kullanici_id")
    private Integer id;

    @Column(name = "kullani_giris_adi")
    private String userName;

    @Column(name = "kullanici_sifre")
    private String password;

    @Column(name = "kullanici_bakiye")
    private Double budget;

    @Column(name = "kullanici_email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "kullanici_rol")
    private UserRole role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private UserInfo userInfo;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.PERSIST})
    @ToString.Exclude
    private OrderBasket orderBaskets;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "user_favorite",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "productId")
    )
    @ToStringExclude
    private Set<Product> userFavoriteProducts;


    public User(String userName, String password, String email,UserRole role,Double budget, UserInfo userInfo) {
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.role=role;
        this.budget=budget;
        this.userInfo=userInfo;
        orders=new ArrayList<>();
        userFavoriteProducts=new HashSet<>();

    }
}
