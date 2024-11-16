package com._candoit.drfood.req;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String id;
    private String password;
}
