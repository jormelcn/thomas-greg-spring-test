package com.jorge.thomas.test.app.money;

import java.util.HashMap;

import org.javamoney.moneta.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class MoneyWriteConverter implements Converter<Money, HashMap<String, Object>> {

  @Override
  @Nullable
  public HashMap<String, Object> convert(@NonNull Money source) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("amount", source.getNumber().toString());
    map.put("currency", source.getCurrency().getCurrencyCode());
    return map;
  }

}
