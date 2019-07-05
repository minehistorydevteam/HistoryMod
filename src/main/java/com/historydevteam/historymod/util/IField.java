package com.historydevteam.historymod.util;

public interface IField<T> {

  String getName();

  void setValue(T value);

  T getValue();

  default T getValue(Object o){
   return getValue();
  }
  default void setValue(Object o, T value){
    setValue(value);
  }
}
