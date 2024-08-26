package com.jorge.thomas.test.app.products.usecases;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import com.jorge.thomas.test.app.product.dtos.ProductForAdminDTO;
import com.jorge.thomas.test.app.product.models.Product;
import com.jorge.thomas.test.app.product.repository.ProductRepository;
import com.jorge.thomas.test.app.product.usecases.AdminReadProduct;

@SpringBootTest
public class AdminReadProductTest {

  @MockBean
  ProductRepository productRepository;

  @Autowired
  AdminReadProduct adminReadProduct;

  @Test
  void shouldThrow_whenNotUserAuthenticated() {
    assertThrows(
        AuthenticationCredentialsNotFoundException.class,
        () -> this.adminReadProduct.perform("id"));
  }

  @Test
  @WithMockUser(authorities = "dumyauthority")
  void shouldThrow_whenAuthoritiesAreNotAllowed() {
    assertThrows(
        AuthorizationDeniedException.class,
        () -> this.adminReadProduct.perform("id"));
  }

  @Test
  @WithMockUser(authorities = "products::read")
  void shouldRun_whenProductsReadAuthorityIsProvided() {
    Mockito
        .when(this.productRepository.findById(anyString()))
        .thenReturn(Optional.of(new Product()));

    assertDoesNotThrow(() -> this.adminReadProduct.perform("id"));
  }

  @Test
  @WithMockUser(authorities = "products::*")
  void shouldRun_whenProductsWildcardAuthorityIsProvided() {
    Mockito
        .when(this.productRepository.findById(anyString()))
        .thenReturn(Optional.of(new Product()));

    assertDoesNotThrow(() -> this.adminReadProduct.perform("id"));
  }

  @Test
  @WithMockUser(authorities = "products::*")
  void should_ObtainProductById() throws MalformedURLException, URISyntaxException {
    String productId = "productId";
    Product product = new Product();
    product.setId(productId);

    Mockito
        .when(this.productRepository.findById(productId))
        .thenReturn(Optional.of(product));

    ProductForAdminDTO result = this.adminReadProduct.perform(productId);

    assertNotNull(result);
    assertEquals(result.getId(), productId);
  }

}
