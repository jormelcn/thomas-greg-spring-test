package com.jorge.thomas.test.app.product.models;

import java.net.URL;
import java.time.LocalDateTime;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Document
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  private String id;

  @NonNull
  private String name;

  @NonNull
  private URL previewImageURL;

  @NonNull
  private Money unitPrice;

  @CreatedDate
  private LocalDateTime createdDate;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  public MonetaryAmount getPriceForUnits(int units) {
    if (units < 0) {
      throw new IllegalArgumentException("units should be positive");
    }
    return this.unitPrice.multiply(units);
  }

}
