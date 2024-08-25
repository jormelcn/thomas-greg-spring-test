package com.jorge.thomas.test.app.product.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.lib.DTOMapper;
import com.jorge.thomas.test.app.product.dtos.AdminUpdateProductRequestDTO;
import com.jorge.thomas.test.app.product.dtos.ProductForAdminDTO;
import com.jorge.thomas.test.app.product.errors.NotFoundProduct;
import com.jorge.thomas.test.app.product.models.Product;
import com.jorge.thomas.test.app.product.repository.ProductRepository;

@Service
@PreAuthorize("hasAnyAuthority('products::*', 'products::update')")
public class AdminUpdateProduct {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  DTOMapper dtoMapper;

  public ProductForAdminDTO perform(AdminUpdateProductRequestDTO request) {
    Product product = this.productRepository
        .findById(request.getId())
        .orElseThrow(() -> new NotFoundProduct());

    if (request.getName() != null)
      product.setName(request.getName());

    if (request.getUnitPrice() != null)
      product.setUnitPrice(request.getUnitPrice());

    if (request.getPreviewImageURL() != null)
      product.setPreviewImageURL(request.getPreviewImageURL());

    this.productRepository.save(product);
    return this.dtoMapper.map(product, ProductForAdminDTO.class);
  }

}
