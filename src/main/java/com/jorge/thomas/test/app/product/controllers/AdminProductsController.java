package com.jorge.thomas.test.app.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.thomas.test.app.product.dtos.AdminCreateProductRequestDTO;
import com.jorge.thomas.test.app.product.dtos.AdminUpdateProductRequestDTO;
import com.jorge.thomas.test.app.product.dtos.ProductForAdminDTO;
import com.jorge.thomas.test.app.product.usecases.AdminCreateProduct;
import com.jorge.thomas.test.app.product.usecases.AdminDeleteProduct;
import com.jorge.thomas.test.app.product.usecases.AdminReadProduct;
import com.jorge.thomas.test.app.product.usecases.AdminUpdateProduct;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/admin/products")
public class AdminProductsController {
  @Autowired
  AdminCreateProduct adminCreateProduct;
  
  @Autowired
  AdminUpdateProduct adminUpdateProduct;
  
  @Autowired
  AdminReadProduct adminReadProduct;
  
  @Autowired
  AdminDeleteProduct adminDeleteProduct;

  @PostMapping("")
  ProductForAdminDTO create(@Valid @RequestBody AdminCreateProductRequestDTO request) {
    return this.adminCreateProduct.perform(request);
  }

  @PutMapping("{id}")
  ProductForAdminDTO update(@PathVariable String id,
      @Valid @RequestBody AdminUpdateProductRequestDTO request) {

    request.setId(id);
    return this.adminUpdateProduct.perform(request);
  }

  @DeleteMapping("{id}")
  void delete(@PathVariable String id) {
    this.adminDeleteProduct.perform(id);
  }

  @GetMapping("{id}")
  ProductForAdminDTO read(@PathVariable String id) {
    return this.adminReadProduct.perform(id);
  }

}
