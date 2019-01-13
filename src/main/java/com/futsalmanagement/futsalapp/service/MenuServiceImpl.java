package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.MenuDao;
import com.futsalmanagement.futsalapp.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Menu> getMenuListByFutsal(int futsal_id) {
        List<Menu> menuListByFutsal = menuDao.findAll().stream()
                .filter(menu -> menu.getFutsal().getFutsal_id() == futsal_id).collect(Collectors.toList());
        return menuListByFutsal;
    }
}
