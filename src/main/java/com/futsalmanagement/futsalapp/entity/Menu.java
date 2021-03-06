package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Menu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menu_id;
    private String menu_item;
    private BigDecimal unit_price;
    private String description;
    @ManyToOne
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;


    public Menu(int menu_id, String menu_item, BigDecimal unit_price, String description) {
        this.menu_id = menu_id;
        this.menu_item = menu_item;
        this.unit_price = unit_price;
        this.description = description;
    }

    public Menu() {
    }

    public Menu inSimpleFormat(){
        return new Menu(this.menu_id, this.menu_item, this.unit_price, this.description);
    }

    public Futsal getFutsal() {
        return futsal;
    }

    public void setFutsal(Futsal futsal) {
        this.futsal = futsal;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_item() {
        return menu_item;
    }

    public void setMenu_item(String menu_item) {
        this.menu_item = menu_item;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", menu_item='" + menu_item + '\'' +
                ", unit_price=" + unit_price +
                ", description='" + description + '\'' +
                '}';
    }
}
