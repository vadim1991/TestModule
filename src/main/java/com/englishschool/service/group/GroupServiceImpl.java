package com.englishschool.service.group;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.group.GroupDaoImpl;
import com.englishschool.entity.Group;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Vadym_Vlasenko on 10/19/2015.
 */
@Service
public class GroupServiceImpl extends GenericManagerImpl<Group, GroupDaoImpl> implements IGroupService<Group> {

    @Autowired
    @Qualifier("groupDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }
}
