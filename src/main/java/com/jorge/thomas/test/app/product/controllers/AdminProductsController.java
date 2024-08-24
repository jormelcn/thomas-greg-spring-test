package com.jorge.thomas.test.app.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.thomas.test.app.product.dtos.ProductForAdminDto;
import com.jorge.thomas.test.app.product.usecases.AdminCreateProduct;
import com.jorge.thomas.test.app.product.usecases.AdminCreateProductRequest;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/admin/products")
public class AdminProductsController {

  @Autowired
  AdminCreateProduct adminCreateProduct;


  @PostMapping("")
  ProductForAdminDto create(@Valid @RequestBody AdminCreateProductRequest request) {
    return this.adminCreateProduct.perform(request);
  }

}
