package com.jorge.thomas.test.app.product.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.lib.DTOMapper;
import com.jorge.thomas.test.app.lib.PageDTO;
import com.jorge.thomas.test.app.product.dtos.AdminSearchProductsRequestDTO;
import com.jorge.thomas.test.app.product.dtos.ProductForAdminDTO;
import com.jorge.thomas.test.app.product.models.Product;
import com.jorge.thomas.test.app.product.repository.ProductRepository;

@Service
@PreAuthorize("hasAnyAuthority('products::*', 'products::read')")
public class AdminSearchProducts {
  @Autowired
  ProductRepository productRepository;

  @Autowired
  DTOMapper dtoMapper;

  public PageDTO<ProductForAdminDTO> perform(AdminSearchProductsRequestDTO request) {
    Page<Product> page = this.productRepository
        .findAll(request.pageable());

    return this.dtoMapper.mapPage(page, ProductForAdminDTO.class);
  }
}
