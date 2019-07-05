package com.historydevteam.historymod.util;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class RegistryUtil {

  public static <T> List<T> getObjectsFromStaticFields(Class from, Class<T> type) {
    List<T> list = new ArrayList<>();

    for (Field field : from.getDeclaredFields()) {
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

  public static <T extends Annotation> List<Pair<T, IField<Object>>> getVariablesMarkedWithAnnotation(Class<T> annotation, Object obj) {
    List<Pair<T, IField<Object>>> list = new ArrayList<>();

    for (Field field : obj.getClass().getDeclaredFields()) {
      if (!Modifier.isStatic(field.getModifiers()) && field.isAnnotationPresent(annotation)) {
        field.setAccessible(true);

        T annot = field.getAnnotation(annotation);
        IField<Object> var = new IField<Object>() {

          @Override
          public String getName() {
            return field.getName();
          }

          @Override
          public void setValue(Object value) {
            try {
              field.set(obj, value);
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            }
          }

          @Override
          public Object getValue() {
            try {
              return field.get(obj);
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            }
            return null;
          }
        };

        list.add(Pair.of(annot, var));
      }
    }

    return list;
  }
}
