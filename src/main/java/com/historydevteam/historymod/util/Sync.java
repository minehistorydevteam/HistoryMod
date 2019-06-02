package com.historydevteam.historymod.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Fields marked with this annotation will be automatically synchronized
 * with the client when the player is in a GUI, useful for progress bars
 * or any other widget that shows the server state in the client
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sync {
  int id();
}
