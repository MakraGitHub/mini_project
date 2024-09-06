package co.prime.mini.pos.models.entity;

import co.prime.mini.pos.models.enums.EnumProdcut;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ItemProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "product_name",length = 150)
    private String productName;

    @Column(name = "product_code",length = 30)
    private String productCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type",length = 10)
    private EnumProdcut prodcutType;

    @Column(name = "barcodeSybology")
    private String barcodeSymbology;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "price")
    private Double price;

    @Column(name = "qty")
    private Integer Qty;

    @Column(name = "alert_qty")
    private Integer alertQty;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image")
    private String image;

    @Column(name = "isoverride_price", columnDefinition = "boolean default false")
    private Boolean isOverridePrice;

    @Column(name = "is_show_in_pos",columnDefinition = "boolean default true")
    private Boolean isShowInPos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand itemBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category itemCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_unit_id")
    private ItemUnit itemUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_item_unit")
    private ItemUnit purchaseItemUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_item_unit")
    private  ItemUnit saleItemUnit;

    @Column(name = "is_deleted",columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isDeleted  = false;
}
