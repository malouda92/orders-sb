package com.example.orders.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.orders.security.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(CLIENT_READ, CLIENT_WRITE, ORDER_READ, ORDER_WRITE, ARTICLE_READ, ARTICLE_WRITE)),
    USER_CLIENT(Sets.newHashSet(CLIENT_READ)),
    USER_ORDER(Sets.newHashSet(ORDER_READ, ORDER_WRITE)),
    USER_ARTICLE(Sets.newHashSet(ARTICLE_READ, ARTICLE_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = permissions
                .stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return authorities;
    }
}
