package co.prime.mini.pos.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SupplierRequest {
    @NotBlank(message = "Name is required!")
    @Length(min = 4, max =250, message = "Supplier local name between 4 to 100 characters")
    private String supplierLocalName;

    @NotBlank(message = "Supplier eng name is required!")
    @Length(min = 4, max = 200, message = "Supplier english name between 4 to 100 characters")
    private String supplierEngName;

    @NotBlank(message = "Supplier email is required!")
    @Length(min = 8, max = 90, message = "Email between 8 to 90 characters")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address")
    private String supplierEmail;

    @NotBlank(message = "Phone is required!")
    @Length(min=8, max = 30, message = "Phone between 8 to 30 characters")
    @Pattern(regexp = "^((\\\\+855)|0)?[1-9][0-9]{7,8}$",message = "Invalid phone number")
    private String supplierPhone;

    @NotBlank(message = "Address is required")
    @Length(min = 10, max = 100, message = "Address between 10 to 100 characters")
    @Pattern(regexp = "^[A-Za-z0-9#.,\\-\\s/]+$", message = "Invalid address format")
    private String supplierAddress;

    @NotBlank(message = "Value Added Tax Number is required!")
    @Length(min = 6, max = 10, message = "Value of tax between 6 to 10 characters")
    @Pattern(regexp = "^B\\d{9}$", message = "VAT Number must start with 'B' followed by 9 digits")
    private String supplierVatNumber;
}
