package com.innova.ecommerce.entity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "kategoriler")
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kategori_id", nullable = false)
    private Integer id;

    @Column(name = "kategori_adi", nullable = false, length = 155)
    private String categoryName;

    @Column(name = "kategori_fotoğrafURL")
    private String categoryImageURL;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.ALL, CascadeType.PERSIST})
    @ToStringExclude
    private List<Product> products;
}
