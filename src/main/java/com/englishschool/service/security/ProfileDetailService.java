package com.englishschool.service.security;

import com.englishschool.entity.Role;
import com.englishschool.entity.TestProfile;
import com.englishschool.service.profile.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Vadym_Vlasenko on 10/20/2015.
 */
@Service("userDetailsService")
public class ProfileDetailService implements UserDetailsService {

    public static final String EMAIL = "email";

    @Autowired
    private IProfileService profileService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        TestProfile profile = profileService.findByEmail(username);
        List<GrantedAuthority> authorities = buildUserAuthority(profile);
        return buildUserForAuthentication(profile, authorities);
    }

    private User buildUserForAuthentication(TestProfile profile, List<GrantedAuthority> authorities) {
        return new User(profile.getEmail(), profile.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(TestProfile profile) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Set<Role> roles = profile.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return grantedAuthorities;
    }

}
