package com.home.ape.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.annotation.Secured;

import com.home.ape.security.Roles;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Secured(Roles.ADMIN_ROLE_WITH_PREFIX)
public @interface RequiresAdminRole {

}
