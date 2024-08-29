package co.prime.mini.pos.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class GeneralSettingRequest {
    @NotBlank(message = "Customer Local name is required!")
    @Length(min = 4, max = 300, message = "Customer Local name between 4 to 70 characters")
    private String siteTitle;
    @NotBlank(message = "Site logo is mandatory")
    @Size(max = 255, message = "Site logo URL cannot be longer than 255 characters")
    private String siteLogo;
    @NotBlank(message = "Phone is required!")
    @Length(min = 8, max =20, message = "Phone between 8 to 20 characters")
    @Pattern(regexp = "^((\\\\+855)|0)?[1-9][0-9]{7,8}$",message = "Invalid phone number")
    private String sitePhone;
    @NotBlank(message = "Address is required!")
    @Length(min = 12, max = 200, message = "Address between 12 to 200 characters")
    @Pattern(regexp = "^[A-Za-z0-9#.,\\-\\s/]+$", message = "Invalid address format")
    private String siteAddress;
}
