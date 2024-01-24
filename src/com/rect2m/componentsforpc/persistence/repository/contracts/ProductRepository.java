package com.rect2m.componentsforpc.persistence.repository.contracts;

import com.rect2m.componentsforpc.persistence.entity.impl.Product;
import com.rect2m.componentsforpc.persistence.repository.Repository;
import java.util.Set;

public interface ProductRepository extends Repository<Product> {

    Set<Product> getAllByManufacturer(String manufacturer);

    Set<Product> getAllByProductName(String productName);

    Set<Product> getAllByDescription(String description);
}
