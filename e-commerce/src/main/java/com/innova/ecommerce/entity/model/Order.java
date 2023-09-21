package com.innova.ecommerce.entity.model;


import com.innova.ecommerce.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "siparisler")
@NoArgsConstructor
public class Order extends BaseEntity{

    public Order(String address, OrderStatus orderStatus, Double totalPrice, User user, Delivery delivery) {
        this.products=new ArrayList<>();
        this.orderStatus = orderStatus;
        this.user = user;
        this.address = address;
        this.totalPrice = totalPrice;
        this.delivery = delivery;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siparis_id")
    private int id;

    @ManyToMany
    @JoinTable(
            name = "siparis_urunler",
            joinColumns = @JoinColumn(name = "siparis_id"),
            inverseJoinColumns = @JoinColumn(name = "urun_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    @Column(name = "siparis_durumu")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kullanici_id")
    @ToString.Exclude
    private User user;

    @Column(name = "siparis_adres")
    private String address;

    @Column(name = "toplam_ucret")
    private Double totalPrice;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Delivery delivery;
}
