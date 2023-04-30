package com.catalogapp.securityservicejwt.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@RequiredArgsConstructor
@Document("roles")
public class Role {

    @Id
    private String id;
    private final String description;

}
