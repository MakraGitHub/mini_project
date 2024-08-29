package co.prime.mini.pos.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CategoryRequest {
    @NotBlank(message = "Name is required!")
    @Length(min =4, max = 150, message = "Name is between 4 to 150 characters")
    private String categoryName;

    @NotBlank(message = "Name is required!")
    @Length(min =4, max = 10, message = "Code is between 4 to 150 characters")
    private String categoryCode;

    private Long parentId;
    private String imagePath;
}
