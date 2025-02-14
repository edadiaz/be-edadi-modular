package com.az.edadi.common_service.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.stream.Collectors;

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
