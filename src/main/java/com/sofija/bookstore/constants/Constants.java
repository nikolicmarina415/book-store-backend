package com.sofija.bookstore.constants;

public final class Constants {

    private Constants() {
    }

    public static final class Role {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String CUSTOMER = "ROLE_CUSTOMER";
        public static final String GOLDEN_CUSTOMER = "ROLE_GOLDEN_CUSTOMER";
    }

    public static final class OrderStatus {
        public static final String PENDING = "PENDING";
        public static final String COMPLETED = "COMPLETED";
    }
}
