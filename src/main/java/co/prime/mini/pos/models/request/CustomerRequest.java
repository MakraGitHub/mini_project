package co.prime.mini.pos.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerRequest {
    @NotBlank(message = "Customer Local name is required!")
    @Length(min = 4, max = 250, message = "Customer Local name between 4 to 70 characters")
    private String customerLocalName;

    @NotBlank(message = "Customer English name is required!")
    @Length(min = 4, max = 200, message = "Customer english name between 8 to 200 characters")
    private String customerEngName;

    @NotBlank(message = "Email is required!")
    @Length(min = 6, max = 40, message = "Email between 6 to 40 characters")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address")
    private String customerEmail;

    @NotBlank(message = "Phone is required!")
    @Length(min = 8, max =20, message = "Phone between 8 to 20 characters")
    @Pattern(regexp = "^((\\\\+855)|0)?[1-9][0-9]{7,8}$",message = "Invalid phone number")
    private  String customerPhone;

    @NotBlank(message = "Address is required!")
    @Length(min = 12, max = 200, message = "Address between 12 to 200 characters")
    @Pattern(regexp = "^[A-Za-z0-9#.,\\-\\s/]+$", message = "Invalid address format")
    private String customerAddress;

}
