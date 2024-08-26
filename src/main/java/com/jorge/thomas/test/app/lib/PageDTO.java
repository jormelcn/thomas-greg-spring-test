package com.jorge.thomas.test.app.lib;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageDTO<T> {
  List<T> content;
  int numberOfElements;
  long totalElements;
  int number;
  int totalPages;
  boolean last;
  boolean first;
  boolean empty;
}
