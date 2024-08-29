package co.prime.mini.pos.models.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cusotmer_id")
    private Long id;
    @Column(name = "customer_local_name")
    private String customerLocalName;
    @Column(name = "customer_eng_name")
    private String customerEngName;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "customer_phone")
    private String customerPhone;
    @Column(name = "customer_address")
    private String customerAddress;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted =false;

}
