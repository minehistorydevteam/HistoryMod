package com.historydevteam.historymod.util;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

  public static <E> void set(Class<E> eClass, String name, @Nullable E instance, Object value) throws ReflectiveOperationException {
    Field f = eClass.getDeclaredField(name);
    boolean a = f.isAccessible();
    f.setAccessible(true);
    f.set(instance, value);
    f.setAccessible(a);
  }

  @SuppressWarnings("unchecked")
  public static <E, V> V get(Class<E> eClass, String name, @Nullable E instance, Class<V> type) throws ReflectiveOperationException {
    Field f = eClass.getDeclaredField(name);
    boolean a = f.isAccessible();
    f.setAccessible(true);
    V result = (V) f.get(instance);
    f.setAccessible(a);
    return result;
  }

  @SuppressWarnings("unchecked")
  public static <E, T> T invoke(Class<E> eClass, String name, @Nullable E instance, Class<T> returnType, Class[] argTypes, Object... args) throws ReflectiveOperationException {
    Method m = eClass.getDeclaredMethod(name, argTypes);
    boolean a = m.isAccessible();
    m.setAccessible(true);
    T result = (T) m.invoke(instance, args);
    m.setAccessible(a);
    return result;
  }

  public static <E> IField<E> getHandle(String fieldName, Class<?> declarer) {
    try {
      Field f = declarer.getDeclaredField(fieldName);
      return new IField<E>() {
        @Override
        public String getName() {
          return fieldName;
        }

        @Override
        public void setValue(E value) {
          setValue(null, value);
        }

        @Override
        public void setValue(Object o, E value) {
          f.setAccessible(true);
          try {
            f.set(o, value);
          } catch(IllegalAccessException e) {}
        }

        @Override
        public E getValue() {
          return getValue(null);
        }

        @Override
        public E getValue(Object o) {
          try {
            return (E) f.get(o);
          } catch(Exception e) {
            return null;
          }
        }
      };
    }catch(ReflectiveOperationException e) {
      return null;
    }
  }
}
