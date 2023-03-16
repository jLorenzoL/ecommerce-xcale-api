package com.xcale.ecommerce.util;

import java.util.UUID;

public class Utils {

    public static String generateOrder() {
        return UUID.randomUUID().toString();
    }
}
