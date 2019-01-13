package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Game;

import java.util.List;

public interface GameService {

    Game addGame(Game game);
    boolean checkifTodayDate(String requestdate);
    boolean checkifValidTime(String requestdate , String requesttime);
    Game findGameById(int game_id);
    List<Game> getGameByFutsal(int futsal_id);

}
