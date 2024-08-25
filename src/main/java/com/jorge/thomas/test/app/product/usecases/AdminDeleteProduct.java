package com.jorge.thomas.test.app.product.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.lib.DTOMapper;
import com.jorge.thomas.test.app.product.dtos.AdminUpdateProductRequestDTO;
import com.jorge.thomas.test.app.product.errors.ImposibleDeleteProduct;
import com.jorge.thomas.test.app.product.errors.NotFoundProduct;
import com.jorge.thomas.test.app.product.models.Product;
import com.jorge.thomas.test.app.product.repository.ProductRepository;

import kotlin.NotImplementedError;

@Service
@PreAuthorize("hasAuthority('products::delete')")
public class AdminDeleteProduct {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  DTOMapper dtoMapper;

  void perform(AdminUpdateProductRequestDTO request) {
    Product product = this.productRepository
        .findById(request.getId())
        .orElseThrow(() -> new NotFoundProduct());

    if(this.thereAreActiveRelations(product)) {
      throw new ImposibleDeleteProduct();
    }

    this.productRepository.delete(product);
  }

  private boolean thereAreActiveRelations(Product product) {
    throw new NotImplementedError();
  }

}
