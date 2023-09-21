package com.innova.ecommerce.entity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Getter
@Setter
@ToString
@Entity
@Table(name = "siparis_sepet")
@NoArgsConstructor
public class OrderBasket extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sepet_id")
    private int id;

    @Column(name = "toplam_sepet_ücreti")
    private double totalPrice;

    @Column(name = "urun_miktar")
    private int quantity;


    @ManyToMany
    @JoinTable(
            name = "sepet_urunler",
            joinColumns = @JoinColumn(name = "sepet_id"),
            inverseJoinColumns = @JoinColumn(name = "urun_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kullanici_id")
    @ToString.Exclude
    private User user;


    @Transient
    public Double getOrderBasketPrice(){
        double sum=0.0;
        for (Product product : this.products) {
            Double price = product.getProductPrice() * quantity;
            sum += price;
        }
       return sum;
    }

    public OrderBasket(User user){
        this.user=user;
    }

}
