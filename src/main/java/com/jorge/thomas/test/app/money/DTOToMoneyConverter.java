package com.jorge.thomas.test.app.money;

import org.javamoney.moneta.Money;
import org.modelmapper.AbstractConverter;

public class DTOToMoneyConverter extends AbstractConverter<MoneyDTO, Money> {
  @Override
  public Money convert(MoneyDTO source) {
    return Money.of(source.amount, source.currency);
  }
}
