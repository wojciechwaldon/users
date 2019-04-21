package com.wojciechwaldon.users.infrastructure.common;

import java.util.UUID;

public class TestTokenGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
