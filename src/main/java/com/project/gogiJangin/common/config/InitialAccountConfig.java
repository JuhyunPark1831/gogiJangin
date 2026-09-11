package com.project.gogiJangin.common.config;

import com.project.gogiJangin.entity.Account;
import com.project.gogiJangin.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InitialAccountConfig {

    @Bean
    public CommandLineRunner initAccount(
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (accountRepository.count() == 0) {

                Account account = Account.builder()
                        .acLoginId("admin")
                        .acPassword(passwordEncoder.encode("1234"))
                        .acName("관리자")
                        .build();

                accountRepository.save(account);
            }
        };
    }
}