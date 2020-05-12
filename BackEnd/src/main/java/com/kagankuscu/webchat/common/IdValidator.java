package com.kagankuscu.webchat.common;

public interface IdValidator {
    default boolean isValidId(Integer id) {
        return id == 0 || id < 0;
    }
}
