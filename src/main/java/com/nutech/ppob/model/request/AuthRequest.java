package com.nutech.ppob.model.request;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 11:45
@Last Modified 10/2/2023 11:45
Version 1.0
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {

    private String email;

    private String first_name;

    private String last_name;

    private String password;

//    private String name;
//
//    private String address;
//
//    private String mobilePhone;

}
