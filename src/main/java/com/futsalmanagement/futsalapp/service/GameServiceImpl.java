package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.GameDao;
import com.futsalmanagement.futsalapp.entity.Game;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Override
    public Game addGame(Game game) {
        Game insertedGame = gameDao.save(game);
        return insertedGame;
    }


    @Override
    public boolean checkifTodayDate(String requestdate) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           Date todaydate1 = new Date();
           //DateTime dtCurrent = new DateTime(todaydate1);
           String todaydate = sdf.format(todaydate1);
           System.out.println("Todays date " + todaydate);
           //return dtRequest.isEqual(dtCurrent);
           return requestdate.equals(todaydate);
    }

    @Override
    public List<Game> getGameByFutsal(int futsal_id) {
        return gameDao.findAll().stream()
                .filter(game -> game.getPlayFutsal().getFutsal_id() == futsal_id)
                .map(game -> game.inSimpleFormat())
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkifValidTime(String requestdate , String requesttime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date toCheckdate = sdf.parse(requestdate + " " + requesttime + ":00");
            Date todaydate = new Date();
            DateTime dt_requestdate = new DateTime(toCheckdate);
            System.out.println("request date " + dt_requestdate);
            DateTime dt_todaydate = new DateTime(todaydate);
            System.out.println("Date time of today " + dt_todaydate);
            System.out.println("check param for equals " + dt_requestdate.isEqual(dt_todaydate));
            System.out.println("check param for greater " + dt_requestdate.isAfter(dt_todaydate));
            return dt_requestdate.isEqual(dt_todaydate) || dt_requestdate.isAfter(dt_todaydate);
        }catch (Exception e){
            e.printStackTrace();;
            return false;
        }
    }

    @Override
    public Game findGameById(int game_id) {
        return gameDao.getOne(game_id);
    }
}
