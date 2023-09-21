package com.innova.ecommerce.entity.model;


import com.innova.ecommerce.entity.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@ToString
@Entity
@Table(name = "teslimatlar")
@NoArgsConstructor
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teslimat_id")
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "siparis_id")
    @ToString.Exclude
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "teslimat_durumu")
    private DeliveryStatus status;

    @Column(name = "teslimat_numarası")
    private String deliveryCode;

    public Delivery(Order order, DeliveryStatus status, String deliveryCode) {
        this.order = order;
        this.status = status;
        this.deliveryCode = deliveryCode;
    }
}
