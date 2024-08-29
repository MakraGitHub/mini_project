package co.prime.mini.pos.models.respone;
import lombok.Data;

@Data
public class SupplierResponse {
    private Integer id;
    private String supplierLocalName;
    private String supplierEngName;
    private String supplierEmail;
    private String supplierPhone;
    private String supplierAddress;
    private String supplierVatNumber;
}
