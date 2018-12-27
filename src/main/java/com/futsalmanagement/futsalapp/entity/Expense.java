package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expense_id;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu_item;
    private int quantity;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Expense(Menu menu_item, int quantity, BigDecimal amount, Game game) {
        this.menu_item = menu_item;
        this.quantity = quantity;
        this.amount = amount;
        this.game = game;
    }

    public Expense(int expense_id,Menu menu_item, int quantity, BigDecimal amount) {
        this.expense_id = expense_id;
        this.menu_item = menu_item;
        this.quantity = quantity;
        this.amount = amount;
    }


    public Expense() {
    }

    public Expense inSimpleFormat(){
        return new Expense(this.expense_id,this.menu_item, this.quantity, this.amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public Menu getMenu_item() {
        return menu_item;
    }

    public void setMenu_item(Menu menu_item) {
        this.menu_item = menu_item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expense_id=" + expense_id +
                ", menu_item=" + menu_item +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}