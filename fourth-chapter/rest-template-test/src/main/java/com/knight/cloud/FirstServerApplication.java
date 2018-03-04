package com.knight.cloud;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FirstServerApplication {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String port = scanner.nextLine();
		new SpringApplicationBuilder(FirstServerApplication.class).properties("server.port=" + port).run(args);
		scanner.close();
	}
}
