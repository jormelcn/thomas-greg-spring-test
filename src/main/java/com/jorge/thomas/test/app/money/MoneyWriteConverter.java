package com.jorge.thomas.test.app.money;

import org.bson.Document;
import org.javamoney.moneta.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class MoneyWriteConverter implements Converter<Money, Document> {

  @Override
  @Nullable
  public Document convert(@NonNull Money source) {
    Document document = new Document();
    document.put("amount", source.getNumber().toString());
    document.put("currency", source.getCurrency().getCurrencyCode());
    document.put("aprox", source.getNumber().doubleValue());
    return document;
  }
}
