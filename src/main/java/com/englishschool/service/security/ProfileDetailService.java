package com.englishschool.service.security;

import com.englishschool.dao.profile.IProfileDao;
import com.englishschool.entity.TestProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vadym_Vlasenko on 10/20/2015.
 */
@Service("userDetailsService")
public class ProfileDetailService implements UserDetailsService {

    //get user from the database, via Hibernate
    @Autowired
    private IProfileDao<TestProfile> profileDao;

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        TestProfile user = (TestProfile) profileDao.findUsersByCriteria("email", username);
        List<GrantedAuthority> authorities =
                buildUserAuthority();

        return buildUserForAuthentication(user, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(TestProfile profile,
                                            List<GrantedAuthority> authorities) {
        return new User(profile.getEmail(), profile.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority() {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
//        for (UserRole userRole : userRoles) {
//            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//        }
//
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
        setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Result.addAll(setAuths);
        return Result;
    }

}
