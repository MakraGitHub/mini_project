package co.prime.mini.pos.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "exchange_rate")
    private Double exchangeRate;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted =false;
}
