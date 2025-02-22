package com.az.edadi.auth.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserBlackList {
    private static Set<UUID> blackList = new HashSet<>();

    public static boolean isBlackListed(UUID userId) {
        return blackList.contains(userId);
    }
    public static void add(UUID userId) {
        blackList.add(userId);
    }
    public static void remove(UUID userId) {
        blackList.remove(userId);
    }

}
