package co.prime.mini.pos.models.respone;

import lombok.Data;

@Data
public class ItemProductResponse {
    private Integer id;
    private Integer brandId;
    private String productCode;
    protected String productName;
    private String productType;
    private String barcodeSymbology;
    private Double cost;
    private Double price;
    private String imagePath;
    private String image;
}
