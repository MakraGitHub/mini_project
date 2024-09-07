package co.prime.mini.pos.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ItemProductRequest {
    private Long brandId;

    @NotBlank(message = "Name is required!")
    @NotNull(message = "Name Can't be null")
    @Length(max = 150, message = "Name maximum 150 characters")
    private String productName;

    @Length(max = 30, message = "Code maximum 30 characters")
    private String productCode;

    @Length(max = 10, message = "Type maximum 10 characters")
    private String productType;

    @Length(max = 50, message = "barcodeSymbology maximum 50 characters")
    private String barcodeSymbology;

    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Cost must be zero or positive")
    private Double cost;

    @NotNull(message = "price is required")
    @PositiveOrZero(message = "Alert quantity must be zero or positive")
    private Double price;

    private String imagePath;
    private String image;

    @NotNull(message = "Item unit is required")
    private Long itemUnitId;

    @NotNull(message = "Purchase item unit is required")
    private Long purchaseItemUnitId;

    @NotNull(message = "Sale item unit is required")
    private Long saleItemUnitId;

}
