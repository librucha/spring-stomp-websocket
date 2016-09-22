package org.librucha.spring.test.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
@EnableScheduling
public class StompWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(StompWebsocketApplication.class, args);
    }
}
