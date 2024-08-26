package com.jorge.thomas.test.app.money;

import org.javamoney.moneta.Money;
import org.modelmapper.AbstractConverter;

public class MoneyToDTOConverter extends AbstractConverter<Money, MoneyDTO> {
  @Override
  public MoneyDTO convert(Money source) {
    MoneyDTO dto = new MoneyDTO();
    dto.setCurrency(source.getCurrency().getCurrencyCode());
    dto.setAmount(source.getNumber().doubleValue());
    return dto;
  }
}
