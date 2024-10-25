package org.example.smartkitchen.security.userPrincipal;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthToken extends AbstractAuthenticationToken {
    private final UserPrincipal userPrincipal;

    public UserPrincipalAuthToken(UserPrincipal userPrincipal) {
        super(userPrincipal.getAuthorities());
        this.userPrincipal = userPrincipal;
        setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.userPrincipal;
    }
}
