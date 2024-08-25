package com.jorge.thomas.test.app.product.dtos;

import org.javamoney.moneta.Money;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class AdminCreateProductRequestDTO {
  @NotBlank
  @NotNull
  @Size(min = 3, max = 50)
  String name;

  @NotNull
  Money unitPrice;

  @NotNull
  URL previewImageURL;
}
