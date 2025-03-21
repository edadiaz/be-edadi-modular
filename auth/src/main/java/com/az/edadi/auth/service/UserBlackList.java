package com.az.edadi.auth.service;

import com.az.edadi.auth.exception.UserBlacklistedException;

import java.util.HashSet;
import java.util.Set;

public class UserBlackList {
    private static Set<String> blackList = new HashSet<>();

    public static void checkUserId(String userId) {
        blackList.stream()
                .filter(userId::equals)
                .findFirst()
                .ifPresent(user -> {
                    throw new UserBlacklistedException("User " + userId + " is blacklisted.");
                });
    }

    public static void add(String userId) {
        blackList.add(userId);
    }

    public static void remove(String userId) {
        blackList.remove(userId);
    }

}
