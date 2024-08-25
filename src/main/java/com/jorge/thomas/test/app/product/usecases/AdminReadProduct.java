package com.jorge.thomas.test.app.product.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.lib.DTOMapper;
import com.jorge.thomas.test.app.product.dtos.ProductForAdminDTO;
import com.jorge.thomas.test.app.product.errors.NotFoundProduct;
import com.jorge.thomas.test.app.product.models.Product;
import com.jorge.thomas.test.app.product.repository.ProductRepository;

@Service
@PreAuthorize("hasAnyAuthority('products::read', 'products::update', 'products::create', 'products::delete')")
public class AdminReadProduct {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  DTOMapper dtoMapper;

  ProductForAdminDTO perform(String id) {
    Product product = this.productRepository.findById(id).orElseThrow(() -> new NotFoundProduct());
    return this.dtoMapper.map(product, ProductForAdminDTO.class);
  }
}
