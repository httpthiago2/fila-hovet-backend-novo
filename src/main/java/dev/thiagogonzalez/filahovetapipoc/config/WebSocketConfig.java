package dev.thiagogonzalez.filahovetapipoc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Habilita o broker simples para o destino "/topic"
        config.enableSimpleBroker("/topic");
        // Define o prefixo de destino da aplicação (as requisições do cliente)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Define o endpoint para a comunicação WebSocket
        registry.addEndpoint("/ws").setAllowedOriginPatterns("http://localhost:3000", "http://localhost:3001").withSockJS();
    }
}
