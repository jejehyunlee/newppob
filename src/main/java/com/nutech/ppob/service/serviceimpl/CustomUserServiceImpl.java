package com.nutech.ppob.service.serviceimpl;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/3/2023 09:19
@Last Modified 10/3/2023 09:19
Version 1.0
*/
import com.nutech.ppob.entity.UserCredential;
import com.nutech.ppob.entity.UserDetailImpl;
import com.nutech.ppob.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return UserDetailImpl.builder()
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .build();
    }
}
