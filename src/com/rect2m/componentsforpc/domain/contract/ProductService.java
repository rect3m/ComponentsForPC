package com.rect2m.componentsforpc.domain.contract;

import com.rect2m.componentsforpc.domain.Service;
import com.rect2m.componentsforpc.persistence.entity.impl.Product;
import java.util.Set;

public interface ProductService extends Service<Product> {

    Set<Product> getAllByManufacturer(String manufacturer);

    Set<Product> getAllByProductName(String productName);

    Set<Product> getAllByDescription(String description);
}
