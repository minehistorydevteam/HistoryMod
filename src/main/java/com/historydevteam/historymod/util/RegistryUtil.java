package com.historydevteam.historymod.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class RegistryUtil {

  public static <T> List<T> getObjectsFromStaticFields(Class file, Class<T> type) {
    List<T> list = new ArrayList<>();

    for (Field field : file.getDeclaredFields()) {
      if (Modifier.isStatic(field.getModifiers()) && type.isAssignableFrom(field.getType())) {
        field.setAccessible(true);
        try {
          //noinspection unchecked
          list.add((T) field.get(null));
        } catch (IllegalAccessException e) {
          System.err.println("Unable to access object in field: " + field.toString());
        }
      }
    }

    return list;
  }

  public static <T> List<T> getObjectsFromFields(Object obj, Class<T> type) {
    List<T> list = new ArrayList<>();

    for (Field field : obj.getClass().getDeclaredFields()) {
      if (!Modifier.isStatic(field.getModifiers()) && type.isAssignableFrom(field.getType())) {
        field.setAccessible(true);
        try {
          //noinspection unchecked
          list.add((T) field.get(obj));
        } catch (IllegalAccessException e) {
          System.err.println("Unable to access object in field: " + field.toString());
        }
      }
    }

    return list;
  }
}
