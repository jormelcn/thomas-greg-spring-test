package com.jorge.thomas.test.app.product.dtos;

import java.net.URL;
import java.time.LocalDateTime;

import org.javamoney.moneta.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForAdminDTO {
  String id;
  String name;
  Money unitPrice;
  URL previewImageURL;
  LocalDateTime createdDate;
  LocalDateTime lastModifiedDate;
}
