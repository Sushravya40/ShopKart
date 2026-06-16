package com.example.shopkart.config;

import com.example.shopkart.entity.Category;
import com.example.shopkart.entity.Product;
import com.example.shopkart.repository.CategoryRepository;
import com.example.shopkart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DatabaseSeeder(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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
            addProduct("iPhone 15 Pro", "The ultimate iPhone with Titanium frame.", 999.00, 50, "https://m.media-amazon.com/images/I/81+GIkwqLIL._AC_UY327_FMwebp_QL65_.jpg", mobiles);
            addProduct("Samsung Galaxy S24 Ultra", "Galaxy AI is here.", 1199.00, 40, "https://m.media-amazon.com/images/I/71CXhVhpM0L._AC_UY327_FMwebp_QL65_.jpg", mobiles);
            addProduct("Google Pixel 8 Pro", "Google's best phone yet.", 899.00, 30, "https://m.media-amazon.com/images/I/71h3zJjHlHL._AC_UY327_FMwebp_QL65_.jpg", mobiles);

            // Laptops
            addProduct("MacBook Pro 14\"", "M3 Pro chip, 18GB RAM.", 1999.00, 20, "https://m.media-amazon.com/images/I/618d5bS2lUL._AC_UY327_FMwebp_QL65_.jpg", laptops);
            addProduct("Dell XPS 15", "Premium Windows laptop with OLED screen.", 1499.00, 25, "https://m.media-amazon.com/images/I/71D0Y7tKzYL._AC_UY327_FMwebp_QL65_.jpg", laptops);
            
            // Headphones
            addProduct("Sony WH-1000XM5", "Industry-leading noise cancellation.", 349.00, 100, "https://m.media-amazon.com/images/I/51aXvjzcukL._AC_UY327_FMwebp_QL65_.jpg", headphones);
            addProduct("AirPods Pro 2", "Magical audio experience.", 249.00, 200, "https://m.media-amazon.com/images/I/61SUj2aKoEL._AC_UY327_FMwebp_QL65_.jpg", headphones);

            // Watches
            addProduct("Apple Watch Series 9", "Smarter. Brighter. Mightier.", 399.00, 60, "https://m.media-amazon.com/images/I/81x2L61oXRL._AC_UY327_FMwebp_QL65_.jpg", watches);
            addProduct("Garmin Fenix 7", "Ultimate multisport GPS watch.", 699.00, 15, "https://m.media-amazon.com/images/I/61I2oE+sKGL._AC_UY327_FMwebp_QL65_.jpg", watches);
        }
    }

    private void addProduct(String name, String desc, double price, int stock, String img, Category category) {
        Product p = new Product();
        p.setProductName(name);
        p.setProductDescription(desc);
        p.setPrice(BigDecimal.valueOf(price));
        p.setStockQuantity(stock);
        p.setCategory(category);
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());
        
        // Use generic image URLs if you want, or the ones provided
        p.setImageUrl(img);
        
        productRepository.save(p);
    }
}
