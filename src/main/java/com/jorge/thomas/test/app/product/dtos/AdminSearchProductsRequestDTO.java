package com.jorge.thomas.test.app.product.dtos;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminSearchProductsRequestDTO {
  @Min(0)
  int page = 0;

  @Min(20)
  @Max(100)
  int sizePerPage = 20;

  Sort.Direction sortDirection = Direction.DESC;
  SortField sortField = SortField.ID;

  public Pageable pageable() {
    return PageRequest.of(
        this.page,
        this.sizePerPage,
        sortDirection,
        sortField.getDatabaseFieldName());
  }

  @AllArgsConstructor
  @Getter
  public enum SortField {
    ID("id"),
    NAME("name"),
    PHONE_NUMBER("phoneNumber");

    private final String databaseFieldName;
  }
}
