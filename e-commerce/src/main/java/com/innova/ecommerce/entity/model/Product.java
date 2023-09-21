package com.innova.ecommerce.entity.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "urunler")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "urun_id")
    private Integer id;

    @Column(name = "urun_adi")
    private String productName;

    @Column(name = "urun_aciklama")
    private String productDescription;

    @Column(name = "urun_fiyat")
    private double productPrice;

    @Column(name = "urun_fotografURL")
    private String productImageURL;

    @Column(name = "urun_miktari")
    private Integer productQuantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "kategori_id")
    @ToString.Exclude
    private Category category;

    @ToString.Exclude
    @ManyToMany(mappedBy = "products")
    private List<OrderBasket> orderBaskets;

    @ToString.Exclude
    @ManyToMany(mappedBy = "products")
    private List<Order> order;

    @ManyToMany(mappedBy = "userFavoriteProducts")
    @ToString.Exclude
    private Set<User> favoriteProductsUser;


}
