package com.example.UserApi;

import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
//		final NgrokClient ngrokClient = new NgrokClient.Builder().build();
//		final CreateTunnel createTunnel = new CreateTunnel.Builder()
//				.withAddr(8080)
//				.build();
//		final Tunnel tunnel = ngrokClient.connect(createTunnel);
	}

}
