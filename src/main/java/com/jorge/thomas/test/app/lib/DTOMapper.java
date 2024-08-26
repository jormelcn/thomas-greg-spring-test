package com.jorge.thomas.test.app.lib;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.jorge.thomas.test.app.money.DTOToMoneyConverter;
import com.jorge.thomas.test.app.money.MoneyToDTOConverter;

import jakarta.annotation.PostConstruct;

@Component
public class DTOMapper extends ModelMapper {

  @PostConstruct
  void postConstructor() {
    this.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE)
        .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

    this.addConverter(new MoneyToDTOConverter());
    this.addConverter(new DTOToMoneyConverter());
  }

  public <S, T> List<T> mapList(Collection<S> source, Class<T> targetClass) {
    return source.stream().map(x -> this.map(x, targetClass)).collect(Collectors.toList());
  }

  public <S, T> PageDTO<T> mapPage(Page<S> source, Class<T> targetClass) {
    PageDTO<T> pageDto = new PageDTO<T>();
    pageDto.content = this.mapList(source.getContent(), targetClass);
    pageDto.setNumberOfElements(source.getNumberOfElements());
    pageDto.setTotalElements(source.getTotalElements());
    pageDto.setNumber(source.getNumber());
    pageDto.setTotalPages(source.getTotalPages());
    pageDto.setLast(source.isLast());
    pageDto.setFirst(source.isFirst());
    pageDto.setEmpty(source.isEmpty());
    return pageDto;
  }
}
