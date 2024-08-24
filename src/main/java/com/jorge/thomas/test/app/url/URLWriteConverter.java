package com.jorge.thomas.test.app.url;

import java.net.URL;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class URLWriteConverter implements Converter<URL, String> {

  @Override
  public String convert(@NonNull URL source) {
    return source.toString();
  }

}
