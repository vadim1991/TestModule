package com.englishschool.dao.group;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.Group;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadym_Vlasenko on 10/19/2015.
 */
@Repository("groupDao")
public class GroupDaoImpl extends GenericMongoDBDaoImpl<Group> implements IGroupDao<Group> {

    public GroupDaoImpl() {
        setClazz(Group.class);
    }

}
