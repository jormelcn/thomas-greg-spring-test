package com.jorge.thomas.test.app.money;

import java.math.BigDecimal;

import org.bson.Document;
import org.javamoney.moneta.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class MoneyReadConverter implements Converter<Document, Money> {

  @Override
  @Nullable
  public Money convert(@NonNull Document source) {
    return Money.of(
        new BigDecimal((String) source.get("amount")),
        (String) source.get("currency"));
  }

}
