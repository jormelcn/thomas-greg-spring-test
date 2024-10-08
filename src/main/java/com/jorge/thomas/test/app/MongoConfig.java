package com.jorge.thomas.test.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.jorge.thomas.test.app.money.MoneyReadConverter;
import com.jorge.thomas.test.app.money.MoneyWriteConverter;
import com.jorge.thomas.test.app.url.URLWriteConverter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoAuditing
public class MongoConfig {
  @Bean
  MongoCustomConversions customConversions(
      MoneyWriteConverter moneyWriteConverter,
      MoneyReadConverter moneyReadConverter,
      URLWriteConverter urlWriteConverter) {
    List<Converter<?, ?>> converters = new ArrayList<>();
    converters.add(urlWriteConverter);
    converters.add(moneyWriteConverter);
    converters.add(moneyReadConverter);
    return new MongoCustomConversions(converters);
  }

  @Bean
  MongoClient mongoClient() {
    return MongoClients.create();
  }
}
