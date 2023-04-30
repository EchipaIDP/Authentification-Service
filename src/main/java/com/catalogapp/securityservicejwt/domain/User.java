package com.catalogapp.securityservicejwt.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@RequiredArgsConstructor
@Document("users")
public class User {

    @Id
    private String id;

    private final String username;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    @DBRef
    private final Role role;
    private final Date createdDate;
    private final Date lastUpdate;

}
