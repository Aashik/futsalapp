package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.MenuDao;
import com.futsalmanagement.futsalapp.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Menu addMenu(Menu menu) {
        Menu inserted_menu = menuDao.save(menu);
        return inserted_menu;
    }

    @Override
    public Menu findMenuById(int menu_id) {
        return menuDao.getOne(menu_id);
    }
}
