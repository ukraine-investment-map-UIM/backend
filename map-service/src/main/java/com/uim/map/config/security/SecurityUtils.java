package com.uim.map.config.security;

import java.util.UUID;

public class SecurityUtils {

    public static UUID getCurrentUserId() {
        return UUID.fromString("00000000-0000-0000-0000-000000000000");
    }
}
