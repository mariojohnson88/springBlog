package com.codeup.springblog.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserWithRoles extends User implements UserDetails {

    public UserWithRoles(User user) {
        super(user); // call the copy constructor defined in the User class
    }


//    Without ALL of these override methods the class will not work and gives an error.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles =""; //This is empty because we are not using the authorization part of the component
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
