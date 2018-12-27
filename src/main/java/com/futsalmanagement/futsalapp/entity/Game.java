package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_id;
    @Size(max = 10)
    private String play_date;
    @Size(max = 10)
    private String play_start_time;
    private double play_duration;
    @Size(max = 20)
    private String game_type;
    @Size(max = 20)
    private String game_status;
    @ManyToOne
    @JoinColumn(name = "futsal_id")
    private Futsal playFutsal;
    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground playGround;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "entry_date", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entry_date;


    public Game(@Size(max = 10) String play_date, @Size(max = 10) String play_start_time, double play_duration, @Size(max = 10) String game_type, @Size(max = 10) String game_status, Futsal playFutsal, Ground playGround) {
        //this.player_full_name = player_full_name;
        this.play_date = play_date;
        this.play_start_time = play_start_time;
        this.play_duration = play_duration;
        this.game_type = game_type;
        this.game_status = game_status;
        this.playFutsal = playFutsal;
        this.playGround = playGround;
    }

    public Game() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getPlay_date() {
        return play_date;
    }

    public void setPlay_date(String play_date) {
        this.play_date = play_date;
    }

    public String getPlay_start_time() {
        return play_start_time;
    }

    public void setPlay_start_time(String play_start_time) {
        this.play_start_time = play_start_time;
    }

    public double getPlay_duration() {
        return play_duration;
    }

    public void setPlay_duration(double play_duration) {
        this.play_duration = play_duration;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getGame_status() {
        return game_status;
    }

    public void setGame_status(String game_status) {
        this.game_status = game_status;
    }

    public Futsal getPlayFutsal() {
        return playFutsal;
    }

    public void setPlayFutsal(Futsal playFutsal) {
        this.playFutsal = playFutsal;
    }

    public Ground getPlayGround() {
        return playGround;
    }

    public void setPlayGround(Ground playGround) {
        this.playGround = playGround;
    }

    @Override
    public String toString() {
        return "Game{" +
                "game_id=" + game_id +
                ", play_date='" + play_date + '\'' +
                ", play_start_time='" + play_start_time + '\'' +
                ", play_duration=" + play_duration +
                ", game_type='" + game_type + '\'' +
                ", game_status='" + game_status + '\'' +
                ", playFutsal=" + playFutsal +
                ", playGround=" + playGround +
                '}';
    }
}
