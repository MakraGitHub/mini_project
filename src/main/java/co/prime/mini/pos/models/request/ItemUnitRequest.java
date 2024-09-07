package co.prime.mini.pos.models.request;

import co.prime.mini.pos.models.enums.EnumItemUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class ItemUnitRequest {

    @NotBlank(message = "Item Unit Code is mandatory")
    @Length(min =4, max = 10, message = "Code is between 4 to 150 characters")
    private String itemUnitCode;

    @NotBlank(message = "Item Unit Name is mandatory")
    @Length(min =4, max = 150, message = "Name is between 4 to 150 characters")

    private String itemUnitName;

    @NotNull(message = "Operator is mandatory")
    private EnumItemUnit operator;

    @NotNull(message = "Operation Value is mandatory")
    private Double operationValue;

    private Long parentId;

}
