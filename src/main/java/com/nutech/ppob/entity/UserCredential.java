package com.nutech.ppob.entity;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 10:37
@Last Modified 10/2/2023 10:37
Version 1.0
*/


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_credential")
@Builder(toBuilder = true)

public class UserCredential {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_credential_id")
    private String idUserCredential;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "m_user_role",
//    joinColumns = @JoinColumn(
//            name = "id_user_credential",
//            referencedColumnName = "user_credential_id"
//    ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id",
//                    referencedColumnName = "id_role"
//            )
//    )
//    private List<Role> roles;

}
