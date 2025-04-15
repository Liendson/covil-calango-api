package br.com.yugiapp;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.time.LocalDateTime;
import java.util.TimeZone;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class YuGiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(YuGiAppApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		System.out.println(LocalDateTime.now());
	}

}
