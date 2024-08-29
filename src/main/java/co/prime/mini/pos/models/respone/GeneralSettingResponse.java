package co.prime.mini.pos.models.respone;

import lombok.Data;

@Data
public class GeneralSettingResponse {
    private Long id;
    private String siteTitle;
    private String siteLogo;
    private String sitePhone;
    private String siteAddress;
}
