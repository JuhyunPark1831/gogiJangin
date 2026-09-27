package com.project.gogiJangin.common.config;

import com.project.gogiJangin.entity.Account;
import com.project.gogiJangin.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
            PasswordEncoder passwordEncoder,
            @Value("${admin.initial-password}") String initialPassword
    ) {
        return args -> {

            if (accountRepository.count() == 0) {

                Account account = Account.builder()
                        .acLoginId("admin")
                        .acPassword(passwordEncoder.encode(initialPassword))
                        .acName("관리자")
                        .build();

                accountRepository.save(account);
            }
        };
    }
}

/* todo
1. 네이버, 구글 검색 결과 확인
2. 주문 링크 설정
 */