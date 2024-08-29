package co.prime.mini.pos.service;

import co.prime.mini.pos.models.entity.Category;

public interface CategoryService {
    Category save(Category category);
    Category getById(Long id);
}
