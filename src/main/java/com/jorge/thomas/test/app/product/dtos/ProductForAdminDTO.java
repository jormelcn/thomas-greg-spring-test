package com.jorge.thomas.test.app.product.dtos;

import java.time.LocalDateTime;

import com.jorge.thomas.test.app.money.MoneyDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForAdminDTO {
  String id;
  String name;
  MoneyDTO unitPrice;
  String previewImageURL;
  LocalDateTime createdDate;
  LocalDateTime lastModifiedDate;
}
