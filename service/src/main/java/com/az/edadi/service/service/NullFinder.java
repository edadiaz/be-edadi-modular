package com.az.edadi.service.service;

import org.springframework.beans.BeanUtils;

import java.beans.FeatureDescriptor;
import java.util.Arrays;

public class NullFinder {
    public static String[] getNullFieldNames(Object sourceClass) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(sourceClass.getClass()))
                .map(FeatureDescriptor::getName)
                .filter(fieldName -> {
                    try {
                        return BeanUtils.getPropertyDescriptor(sourceClass.getClass(), fieldName)
                                .getReadMethod()
                                .invoke(sourceClass) == null;
                    } catch (Exception e) {
                        return true;
                    }
                })
                .toArray(String[]::new);
    }
}
