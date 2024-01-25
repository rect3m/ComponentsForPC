package com.rect2m.componentsforpc.domain.impl;

import com.rect2m.componentsforpc.domain.contract.ProductService;
import com.rect2m.componentsforpc.persistence.entity.impl.Product;
import com.rect2m.componentsforpc.persistence.repository.contracts.ProductRepository;
import java.util.Set;

public class ProductServiceImpl
        extends GenericService<Product>
        implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public Set<Product> getAllByManufacturer(String manufacturer) {
        return productRepository.getAllByManufacturer(manufacturer);
    }

    @Override
    public Set<Product> getAllByProductName(String productName) {
        return productRepository.getAllByProductName(productName);
    }

    @Override
    public Set<Product> getAllByDescription(String description) {
        return productRepository.getAllByDescription(description);
    }
}