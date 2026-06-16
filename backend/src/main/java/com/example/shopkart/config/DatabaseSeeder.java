package com.example.shopkart.config;

import com.example.shopkart.entity.Category;
import com.example.shopkart.repository.CategoryRepository;
import com.example.shopkart.repository.ProductRepository;
import com.example.shopkart.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final AdminProductService adminProductService;

    @Autowired
    public DatabaseSeeder(CategoryRepository categoryRepository, ProductRepository productRepository, AdminProductService adminProductService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.adminProductService = adminProductService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed Categories
        if (categoryRepository.count() == 0) {
            System.out.println("Seeding Categories...");
            categoryRepository.save(new Category("Mobiles"));
            categoryRepository.save(new Category("Laptops"));
            categoryRepository.save(new Category("Headphones"));
            categoryRepository.save(new Category("Smart Watches"));
        }

        // Seed Products
        if (productRepository.count() == 0) {
            System.out.println("Seeding Products...");
            
            Category mobiles = categoryRepository.findByCategoryName("Mobiles").orElseThrow();
            Category laptops = categoryRepository.findByCategoryName("Laptops").orElseThrow();
            Category headphones = categoryRepository.findByCategoryName("Headphones").orElseThrow();
            Category watches = categoryRepository.findByCategoryName("Smart Watches").orElseThrow();

            // Mobiles
            adminProductService.addProduct("iPhone 15 Pro", "The ultimate iPhone with Titanium frame.", BigDecimal.valueOf(999.00), 50, mobiles.getCategoryId(), "https://m.media-amazon.com/images/I/81+GIkwqLIL._AC_UY327_FMwebp_QL65_.jpg");
            adminProductService.addProduct("Samsung Galaxy S24 Ultra", "Galaxy AI is here.", BigDecimal.valueOf(1199.00), 40, mobiles.getCategoryId(), "https://m.media-amazon.com/images/I/71CXhVhpM0L._AC_UY327_FMwebp_QL65_.jpg");
            adminProductService.addProduct("Google Pixel 8 Pro", "Google's best phone yet.", BigDecimal.valueOf(899.00), 30, mobiles.getCategoryId(), "https://m.media-amazon.com/images/I/71h3zJjHlHL._AC_UY327_FMwebp_QL65_.jpg");

            // Laptops
            adminProductService.addProduct("MacBook Pro 14\"", "M3 Pro chip, 18GB RAM.", BigDecimal.valueOf(1999.00), 20, laptops.getCategoryId(), "https://m.media-amazon.com/images/I/618d5bS2lUL._AC_UY327_FMwebp_QL65_.jpg");
            adminProductService.addProduct("Dell XPS 15", "Premium Windows laptop with OLED screen.", BigDecimal.valueOf(1499.00), 25, laptops.getCategoryId(), "https://m.media-amazon.com/images/I/71D0Y7tKzYL._AC_UY327_FMwebp_QL65_.jpg");
            
            // Headphones
            adminProductService.addProduct("Sony WH-1000XM5", "Industry-leading noise cancellation.", BigDecimal.valueOf(349.00), 100, headphones.getCategoryId(), "https://m.media-amazon.com/images/I/51aXvjzcukL._AC_UY327_FMwebp_QL65_.jpg");
            adminProductService.addProduct("AirPods Pro 2", "Magical audio experience.", BigDecimal.valueOf(249.00), 200, headphones.getCategoryId(), "https://m.media-amazon.com/images/I/61SUj2aKoEL._AC_UY327_FMwebp_QL65_.jpg");

            // Watches
            adminProductService.addProduct("Apple Watch Series 9", "Smarter. Brighter. Mightier.", BigDecimal.valueOf(399.00), 60, watches.getCategoryId(), "https://m.media-amazon.com/images/I/81x2L61oXRL._AC_UY327_FMwebp_QL65_.jpg");
            adminProductService.addProduct("Garmin Fenix 7", "Ultimate multisport GPS watch.", BigDecimal.valueOf(699.00), 15, watches.getCategoryId(), "https://m.media-amazon.com/images/I/61I2oE+sKGL._AC_UY327_FMwebp_QL65_.jpg");
        }
    }
}
