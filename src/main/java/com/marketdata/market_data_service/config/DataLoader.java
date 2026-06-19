package com.marketdata.market_data_service.config;

import com.marketdata.market_data_service.entity.User;
import com.marketdata.market_data_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final PasswordEncoder encoder;

    @Bean
    CommandLineRunner load(UserRepository repo) {

        return args -> {

            if (repo.findByUsername("admin").isEmpty()) {

                repo.save(
                        User.builder()
                                .username("admin")
                                .password(
                                        encoder.encode("admin123"))
                                .build()
                );
            }
        };
    }
}
