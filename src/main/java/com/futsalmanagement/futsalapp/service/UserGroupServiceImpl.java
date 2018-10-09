package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.UserGroupDao;
import com.futsalmanagement.futsalapp.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupDao userGroupDao;

    @Override
    public UserGroup getOwner() {
    List<UserGroup> usergrouplist = userGroupDao.findAll();
    UserGroup owneruserGroup = usergrouplist.stream().
            filter(userGroup -> userGroup.getUser_group_id() == 1 && userGroup.getUser_group_name().equals("OWNER"))
            .findAny().orElse(null);
    return owneruserGroup;
    }

    @Override
    public UserGroup getEmployee() {
        List<UserGroup> usergrouplist = userGroupDao.findAll();
        UserGroup employeeUserGroup = usergrouplist.stream().
                filter(userGroup -> userGroup.getUser_group_id() == 2 && userGroup.getUser_group_name().equals("EMP"))
                .findAny().orElse(null);
        return employeeUserGroup;
    }
}
