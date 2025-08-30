package com.richardmogou.Blog.Api.richardmogou99.gmail.com;

import org.springframework.boot.SpringApplication;

public class TestBlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(BlogApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
