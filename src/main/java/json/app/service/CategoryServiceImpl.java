package json.app.service;

import com.google.gson.Gson;
import json.app.Util.contract.ValidatorUtil;
import json.app.domain.model.binding.CategorySeedDto;
import json.app.domain.entity.Category;
import json.app.repository.CategoryRepository;
import json.app.service.contract.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil,
                               ModelMapper modelMapper, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCategories(String jsonCategories) {
        if(this.categoryRepository.count() != 0){
            return;
        }
        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(jsonCategories, CategorySeedDto[].class);

        for(CategorySeedDto categorySeedDto : categorySeedDtos){
            if(this.validatorUtil.ifNotValidPrintViolations(categorySeedDto)){
                return;
            }
            Category category = this.modelMapper.map(categorySeedDto, Category.class);
            System.out.println();
            this.categoryRepository.saveAndFlush(category);
        }
    }

    @Override
    public int getCount() {
        return Math.toIntExact(this.categoryRepository.count());
    }

    @Override
    public Category findCategoryById(Integer id){
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Category> getRandomCategories(){
        Set<Category> categories = new HashSet<>();
        Random random = new Random();
        System.out.println();
        for (int i = 0; i < random.nextInt(this.getCount()) + 1; i++) {
            categories.add(this.getRandomCategory());
        }
        return categories;
    }
    @Override
    public Category getRandomCategory(){
        Random random = new Random();
        return this.findCategoryById(random.nextInt(this.getCount()) + 1);
    }


    //Query 3
    @Override
    public String getCategoriesByProductCount() {
        return this.gson.toJson(this.categoryRepository.categoryByProductCount());
    }
}
