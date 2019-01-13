package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Menu;

import java.util.List;

public interface MenuService {

    Menu addMenu(Menu menu);
    Menu findMenuById(int menu_id);
    List<Menu> getMenuListByFutsal(int futsal_id);

}
