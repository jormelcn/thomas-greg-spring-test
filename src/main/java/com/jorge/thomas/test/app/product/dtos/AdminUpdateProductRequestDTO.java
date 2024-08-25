package com.jorge.thomas.test.app.product.dtos;

import java.net.URL;

import org.javamoney.moneta.Money;
import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminUpdateProductRequestDTO {
  @Id
  @NotNull
  private String id;
  private String name;
  private URL previewImageURL;
  private Money unitPrice;
}
