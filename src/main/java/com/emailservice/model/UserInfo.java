package com.emailservice.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfo {

    private String firstName;
    private String secondName;
    private String email;
    private boolean hasPrivileges;

}
