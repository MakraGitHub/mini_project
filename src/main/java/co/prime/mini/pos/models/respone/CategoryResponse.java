package co.prime.mini.pos.models.respone;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private Integer id;
    private String categoryName;
    private String categoryCode;
    private Long parentId;
    private String imagePath;
    private List<CategoryResponse> children;
}
