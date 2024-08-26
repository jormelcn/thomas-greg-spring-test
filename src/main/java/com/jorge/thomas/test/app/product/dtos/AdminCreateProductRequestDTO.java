package com.jorge.thomas.test.app.product.dtos;

import com.jorge.thomas.test.app.money.MoneyDTO;

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
  MoneyDTO unitPrice;

  @NotNull
  URL previewImageURL;
}
