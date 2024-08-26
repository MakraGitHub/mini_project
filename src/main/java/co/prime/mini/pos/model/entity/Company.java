package co.prime.mini.pos.model.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "companys")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;
    @Column(name ="company_local_name")
    private String companyLocalName;

    @Column(name = "customer_eng_name")
    private String customerEngName;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "company_phone")
    private String companyPhone;

    @Column(name = "company_vat_number")
    private String vatNumber;

    @Column(name = "company_image_path")
    private String imagePath;

    @Column(name = "image")
    private String image;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted = false;

}
