package json.app.service.contract;

import json.app.domain.entity.Category;

import java.util.Set;

public interface CategoryService {

    void seedCategories(String jsonCategories);

    int getCount();

    Category findCategoryById(Integer id);

    Set<Category> getRandomCategories();

    Category getRandomCategory();


    //Query 3
    String getCategoriesByProductCount();
}
