package co.prime.mini.pos.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerRequest {
    @NotBlank(message = "Customer Local name is required!")
    @Length(min = 4, max = 70, message = "Customer Local name between 4 to 70 characters")
    private String customerLocalName;

    @NotBlank(message = "Customer English name is required!")
    @Length(min = 8, max = 200, message = "Customer english name between 8 to 200 characters")
    private String customerEngName;

    @NotBlank(message = "Email is required!")
    @Length(min = 6, max = 40, message = "Email between 6 to 40 characters")
    private String customerEmail;

    @NotBlank(message = "Phone is required!")
    @Length(min = 8, max =20, message = "Phone between 8 to 20 characters")
    private  String customerPhone;

    @NotBlank(message = "Address is required!")
    @Length(min = 12, max = 200, message = "Address between 12 to 200 characters")
    private String customerAddress;

}
