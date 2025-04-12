package br.com.yugiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class YuGiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(YuGiAppApplication.class, args);
	}

}
