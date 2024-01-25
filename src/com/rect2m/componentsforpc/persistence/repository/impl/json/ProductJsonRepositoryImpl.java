package com.rect2m.componentsforpc.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rect2m.componentsforpc.persistence.entity.impl.Product;
import com.rect2m.componentsforpc.persistence.repository.contracts.ProductRepository;
import java.util.Set;
import java.util.stream.Collectors;

class ProductJsonRepositoryImpl
        extends GenericJsonRepository<Product>
        implements ProductRepository {

    public ProductJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.PRODUCTS.getPath(), TypeToken
                .getParameterized(Set.class, Product.class)
                .getType());
    }

    @Override
    public Set<Product> getAllByManufacturer(String manufacturer) {
        return entities.stream().filter(p -> p.getManufacturer().equals(manufacturer))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Product> getAllByProductName(String productName) {
        return entities.stream().filter(p -> p.getProductName().equals(productName))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Product> getAllByDescription(String description) {
        return entities.stream().filter(p -> p.getDescription().equals(description))
                .collect(Collectors.toSet());
    }
}