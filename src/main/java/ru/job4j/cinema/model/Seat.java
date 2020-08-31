package ru.job4j.cinema.model;

import java.util.Objects;

/**
 * status == false => mean: seat if FREE
 * status == true  => mean: seat if ACCEPTED
 */
public class Seat {
    private int id;
    private int row;
    private int column;
    private int hallId;
    private boolean status;

    public Seat(int id, int row, int column, int hallId, boolean status) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.hallId = hallId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seat seat1 = (Seat) o;
        return id == seat1.id
                && row == seat1.row
                && column == seat1.column
                && hallId == seat1.hallId
                && status == seat1.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, column, hallId, status);
    }
}
