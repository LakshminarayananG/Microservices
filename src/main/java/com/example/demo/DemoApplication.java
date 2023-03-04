package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	MessageSource messageSource;

	@GetMapping(path = "custom/message")
	public String welcomeMessage(){
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("welcome.message",
				null,"default welcome message",locale);
	}

}
