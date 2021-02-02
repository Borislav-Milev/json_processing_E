package json.app.controller;

import json.app.Util.contract.FileIO;
import json.app.queryManager.Manager;
import json.app.service.contract.CategoryService;
import json.app.service.contract.ProductService;
import json.app.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static json.app.constant.FilePath.*;

@Component
public class Controller implements CommandLineRunner {

    private final Manager manager;
    private final FileIO fileIO;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public Controller(Manager manager, FileIO fileIO, UserService userService,
                      CategoryService categoryService, ProductService productService) {
        this.manager = manager;
        this.fileIO = fileIO;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        this.seedUsers();
        this.seedCategories();
        this.seedProducts();

        this.manager.run();
    }

    private void seedUsers() {
        String usersFileContent = this.fileIO.readFile(USERS_PATH);
        this.userService.seedUser(usersFileContent);
    }

    private void seedCategories() {
        String categoriesFileContent = this.fileIO.readFile(CATEGORIES_PATH);
        this.categoryService.seedCategories(categoriesFileContent);
    }

    private void seedProducts() {
        String productsFileContent = this.fileIO.readFile(PRODUCTS_PATH);
        this.productService.seedProducts(productsFileContent);
    }
}
