package co.prime.mini.pos.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", length = 150)
    private String categoryName;

    @Column(name = "category_code",length = 10)
    private String categoryCode;

    @Column(name = "image_path",length = 250)
    private String categoryPath;

    //make with sub Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category  parent;

    @OneToMany(mappedBy = "parent",cascade =CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "is_deleted = false")
    @JsonBackReference
    private List<Category> children;

    @Column(name = "is_deleted",
            columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isDeleted = false;

}
