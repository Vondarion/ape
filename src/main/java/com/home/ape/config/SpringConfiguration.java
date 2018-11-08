package com.home.ape.config;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * The Class SpringConfiguration.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfiguration {

	/** The message resource bundle name. */
	@Value("${messageResourceBundleName}")
	String	messageResourceBundleName;

	/** The message resource bundle encoding. */
	@Value("${messageResourceBundleEncoding}")
	String	messageResourceBundleEncoding;

	/**
	 * Message source.
	 *
	 * @return the message source
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(messageResourceBundleName);
		messageSource.setDefaultEncoding(messageResourceBundleEncoding);
		return messageSource;
	}

	/**
	 * Validator.
	 *
	 * @return the local validator factory bean
	 */
	@Bean
	public Validator validator() {
		LocalValidatorFactoryBean validatorBean = new LocalValidatorFactoryBean();
		validatorBean.setValidationMessageSource(messageSource());
		return validatorBean;
	}

}
