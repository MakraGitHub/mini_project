package co.prime.mini.pos.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CompanyRequest {

    @NotBlank(message = "Company Local name is required!")
    @Length(min = 4, max = 250, message = "Company Local name between 4 to 70 characters")
    private String companyLocalName;

    @NotBlank(message = "Company English name is required!")
    @Length(min = 4, max = 200, message = "Company english name between 8 to 200 characters")
    private String companyEngName;

    @NotBlank(message = "Company English name is required!")
    @Length(min = 4, max = 200, message = "Company english name between 8 to 200 characters")
    private String companyEmail;

    @NotBlank(message = "Phone is required!")
    @Length(min = 8, max =20, message = "Phone between 8 to 20 characters")
    @Pattern(regexp = "^((\\\\+855)|0)?[1-9][0-9]{7,8}$",message = "Invalid phone number")
    private  String companyPhone;

    @NotBlank(message = "Value Added Tax Number is required!")
    @Length(min = 6, max = 10, message = "Value of tax between 6 to 10 characters")
    @Pattern(regexp = "^A\\d{9}$", message = "VAT Number must start with 'A' followed by 9 digits")
    private String vatNumber;

    private String imagePath;
    private String image;
}
