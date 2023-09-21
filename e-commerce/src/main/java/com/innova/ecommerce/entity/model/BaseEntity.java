package com.innova.ecommerce.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "olusturma_tarihi")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "guncelleme_tarihi")
    private Date updateDate;
}
