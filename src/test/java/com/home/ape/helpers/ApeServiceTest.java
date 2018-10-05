package com.home.ape.helpers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles
public @interface ApeServiceTest {
	@AliasFor(annotation = ActiveProfiles.class, attribute = "profiles")
	String[] activeProfiles() default { "test" };

	@AliasFor(annotation = SpringBootTest.class, attribute = "webEnvironment")
	WebEnvironment webEnvironment() default WebEnvironment.MOCK;
}
