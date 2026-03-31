package com.example.javaspringrestapi.services;

import com.example.javaspringrestapi.entities.Product;
import com.example.javaspringrestapi.repos.ProductRepo;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final ObjectProvider<Product> customProduct;
    private final ObjectProvider<Product> fakeProduct;

    public ProductService(ProductRepo productRepo,
                          ObjectProvider<Product> customProduct,
                          @Qualifier("fakeProduct") ObjectProvider<Product> fakeProduct) {
        this.productRepo = productRepo;
        this.customProduct = customProduct;
        this.fakeProduct = fakeProduct;
    }

    public Product getCustom(String name, String description, BigDecimal price) {
        Product p = customProduct.getObject();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        return p;
    }

    public Product getFake() {
        return fakeProduct.getObject();
    }

    public Product updateProduct(Product product, Map<String, Object> updates)
            throws NoSuchFieldException, IllegalAccessException {

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            Field field = Product.class.getDeclaredField(key);
            field.setAccessible(true);

            Class<?> fieldType = field.getType();

            if (fieldType.equals(BigDecimal.class) && value instanceof Number) {
                value = BigDecimal.valueOf(((Number) value).doubleValue());
            }

            field.set(product, value);
        }

        productRepo.save(product);
        return product;
    }
}
