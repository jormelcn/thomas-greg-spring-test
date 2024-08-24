package com.jorge.thomas.test.app.product.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.lib.DTOMapper;
import com.jorge.thomas.test.app.product.dtos.ProductForAdminDTO;
import com.jorge.thomas.test.app.product.models.Product;
import com.jorge.thomas.test.app.product.repository.ProductRepository;

@Service
public class AdminCreateProduct {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private DTOMapper mapper;

  public ProductForAdminDTO perform(AdminCreateProductRequestDTO request) {
    Product product = new Product(request.name, request.previewImageURL, request.unitPrice);
    this.productRepository.insert(product);
    return this.mapper.map(product, ProductForAdminDTO.class);
  }

}
