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

/* todo
1. 홈페이지 하단 정보 변경
2. 관리자 사이트 비밀번호 설정
3. 네이버, 구글 검색 결과 확인
4. 주문 링크 설정
5. 가맹문의 수정시
 */