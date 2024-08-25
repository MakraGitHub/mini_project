package co.prime.mini.pos.model.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "supplies")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long id;
    @Column(name = "supplier_local_name")
    private String supplierLocalName;
    @Column(name = "supplier_eng_name")
    private String supplierEngName;
    @Column(name = "suppler_email")
    private String supplierEmail;
    @Column(name = "supplier_phone")
    private String supplierPhone;
    @Column(name = "supplier_address")
    private String supplierAddress;
    @Column(name = "supplier_vat_number")
    private String supplierVatNumber;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted = false;
}
