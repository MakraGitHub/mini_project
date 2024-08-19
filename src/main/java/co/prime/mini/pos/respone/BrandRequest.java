package co.prime.mini.pos.respone;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class BrandRequest {
    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, max = 150, message = "Name be between 3 and 150 characters")
    private String name;
}