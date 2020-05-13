package com.tech.blog;

import com.couchbase.client.java.json.JsonValueModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tech.blog.model.InterviewQuestion;
import com.tech.blog.model.JsonKeyDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TechBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechBlogApplication.class, args);
	}

	@Bean
	public ObjectMapper provideMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		mapper.registerModule(new JavaTimeModule());
		mapper.registerModule(new JsonValueModule());
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addKeyDeserializer(InterviewQuestion.class, new JsonKeyDeserializer());
		mapper.registerModule(simpleModule);
		return mapper;
	}

}
