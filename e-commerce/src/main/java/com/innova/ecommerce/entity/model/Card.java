package com.innova.ecommerce.entity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kart")
public class Card extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kart_id", nullable = false)
    private Integer id;

    @Column(name = "kart_numara")
    private String cardNumber;

    @Column(name = "kart_sahibi_adı")
    private String cardOwnerName;

    @Column(name = "kart_sahibi_soyadı")
    private String cardOwnerSurname;

    @Column(name = "kart_son_kullanma_tarihi")
    private String expiredDate;

    @Column(name = "kart_doğrulama_kodu")
    private String CVCode;

    @Column(name = "kart_bakiyesi")
    private  Double budget;
}
