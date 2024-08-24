package com.jorge.thomas.test.app.lib;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DTOMapper extends ModelMapper {

  @PostConstruct
  void postConstructor() {
    this.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE)
        .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
  }

  public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
    return source.stream().map(x -> this.map(x, targetClass)).collect(Collectors.toList());
  }

}
