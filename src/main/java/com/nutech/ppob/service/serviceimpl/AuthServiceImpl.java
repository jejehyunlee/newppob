package com.nutech.ppob.service.serviceimpl;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 15:00
@Last Modified 10/2/2023 15:00
Version 1.0
*/

import com.nutech.ppob.entity.Customer;
import com.nutech.ppob.entity.UserCredential;
import com.nutech.ppob.entity.UserDetailImpl;
import com.nutech.ppob.handler.ResponeHandler;
import com.nutech.ppob.model.request.AuthRequest;
import com.nutech.ppob.model.request.LoginRequest;
import com.nutech.ppob.model.response.LoginResponse;
import com.nutech.ppob.repository.UserCredentialRepository;
import com.nutech.ppob.security.BCryptUtils;
import com.nutech.ppob.security.JwtUtils;
import com.nutech.ppob.service.AuthService;
import com.nutech.ppob.service.AuthValidation;
import com.nutech.ppob.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthValidation validateAuthRequest;

    private final UserCredentialRepository userCredentialRepository;

    private final AuthenticationManager authenticationManager;

    private final BCryptUtils bCryptUtils;

    private final CustomerService customerService;

    private final JwtUtils jwtUtils;


    @Transactional(rollbackOn = Exception.class)
    @Override
    public ResponseEntity<Object> register(AuthRequest authRequest) {

            ResponseEntity<Object> validationResponse = validateAuthRequest.validateRegisterRequest(authRequest);
            if (validationResponse != null) {
                return validationResponse; // Return error jika validasi gagal
            }

            UserCredential userCredential = UserCredential.builder()
                    .email(authRequest.getEmail())
                    .password(bCryptUtils.hashPassword(authRequest.getPassword()))
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);
            Customer customer = Customer.builder()
                    .first_name(authRequest.getFirst_name())
                    .last_name(authRequest.getLast_name())
                    .email(authRequest.getEmail())
                    .userCredential(userCredential)
                    .build();
            customerService.create(customer);

            ResponeHandler<Object> response = new ResponeHandler<>(0, "Berhasil register silahkan login", authRequest.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> login(LoginRequest loginRequest) {

        try {


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();

        String token = jwtUtils.generateToken(userDetail.getEmail());

        LoginResponse loginResponse = LoginResponse.builder()
                .token(token)
                .build();

        ResponeHandler<Object> response = new ResponeHandler<>(0, "Sukses Login", loginResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch (BadCredentialsException e) {
            // Jika username atau password salah
            ResponeHandler<Object> response = new ResponeHandler<>(103, "Username atau Password salah", null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

        } catch (UsernameNotFoundException e) {
            // Jika username tidak ditemukan di database
            ResponeHandler<Object> response = new ResponeHandler<>(104, "Username tidak ditemukan", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            // Jika terjadi error lain
            ResponeHandler<Object> response = new ResponeHandler<>(500, "Terjadi kesalahan pada server", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
