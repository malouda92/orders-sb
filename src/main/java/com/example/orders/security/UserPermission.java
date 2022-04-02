package com.example.orders.security;

public enum UserPermission {
    CLIENT_READ("CLIENT_READ"),
    CLIENT_WRITE("CLIENT_WRITE"),
    ORDER_READ("ORDER_READ"),
    ORDER_WRITE("ORDER_WRITE"),
    ARTICLE_READ("ARTICLE_READ"),
    ARTICLE_WRITE("ARTICLE_WRITE");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
