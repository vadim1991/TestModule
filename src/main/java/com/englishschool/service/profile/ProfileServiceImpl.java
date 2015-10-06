package com.englishschool.service.profile;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.profile.ProfileDaoImpl;
import com.englishschool.entity.TestProfile;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 10/5/2015.
 */
@Service
public class ProfileServiceImpl extends GenericManagerImpl<TestProfile, ProfileDaoImpl> implements IProfileService<TestProfile> {

    @Autowired
    @Qualifier("profileDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

}
