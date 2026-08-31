package com.project.gogiJangin.service;

import com.project.gogiJangin.common.exception.CustomException;
import com.project.gogiJangin.common.exception.ErrorCode;
import com.project.gogiJangin.entity.Account;
import com.project.gogiJangin.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Account account = accountRepository.findByAcLoginId(username)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.NOT_FOUNT_ACCOUNT));

        return User.builder()
                .username(account.getAcLoginId())
                .password(account.getAcPassword())
                .build();
    }
}
