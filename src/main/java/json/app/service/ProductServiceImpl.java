package json.app.service;

import com.google.gson.Gson;
import json.app.Util.contract.ValidatorUtil;
import json.app.domain.model.binding.ProductSeedDto;
import json.app.domain.entity.Product;
import json.app.domain.entity.User;
import json.app.domain.model.view.query1.ProductInRangeDto;
import json.app.repository.ProductRepository;
import json.app.service.contract.CategoryService;
import json.app.service.contract.ProductService;
import json.app.service.contract.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService,
                              UserService userService, ValidatorUtil validatorUtil,
                              ModelMapper modelMapper, Gson gson) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Transactional
    @Override
    public void seedProducts(String jsonProducts) {
        if (this.productRepository.count() != 0) {
            return;
        }
        ProductSeedDto[] productSeedDtos = this.gson.fromJson(jsonProducts, ProductSeedDto[].class);

        for (ProductSeedDto productSeedDto : productSeedDtos) {
            if (this.validatorUtil.ifNotValidPrintViolations(productSeedDto)) {
                return;
            }
            System.out.println();
            Product product = this.modelMapper.map(productSeedDto, Product.class);
            product.getCategories().addAll(this.categoryService.getRandomCategories());
            User buyer = this.userService.getRandomUser();
            User seller = this.userService.getRandomUser();
            if (buyer.getId() % 7 != 0 && !buyer.getId().equals(seller.getId())) {
                product.setBuyer(buyer);
            }
            product.setSeller(seller);
            this.productRepository.saveAndFlush(product);
        }
    }

    //Query 1
    @Override
    public String productsInRange() {
        Set<Product> products = this.productRepository
                .findAllByPriceBetweenAndBuyerOrderByPrice(
                        BigDecimal.valueOf(500), BigDecimal.valueOf(1000), null);

        List<ProductInRangeDto> productInRangeDtos = new ArrayList<>();
        for (Product product : products) {
            productInRangeDtos.add(this.modelMapper.map(product, ProductInRangeDto.class));
        }
        System.out.println();
        return this.gson.toJson(productInRangeDtos);
    }
}
