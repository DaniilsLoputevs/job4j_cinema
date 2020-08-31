package ru.job4j.cinema.model;

import java.util.Objects;

public class Account {
    private int id;
    private String name;
    private int phone;
    private int seatId;

    public Account(String name, int phone, int seatId) {
        this.name = name;
        this.phone = phone;
        this.seatId = seatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return id == account.id
                && phone == account.phone
                && seatId == account.seatId
                && Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, seatId);
    }
}
