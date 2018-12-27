package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Menu;

public interface MenuService {

    Menu addMenu(Menu menu);
    Menu findMenuById(int menu_id);

}
