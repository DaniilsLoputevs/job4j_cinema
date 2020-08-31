package ru.job4j.cinema.store;

import ru.job4j.cinema.db.PsqlConnectPool;
import ru.job4j.cinema.model.Seat;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DbStoreSeat implements DbStore<Seat> {
    private static final class Lazy {
        private static final DbStoreSeat INST = new DbStoreSeat();
    }

    public static DbStoreSeat instOf() {
        return DbStoreSeat.Lazy.INST;
    }

    @Override
    public Collection<Seat> getAll() {
        List<Seat> posts = new ArrayList<>();
        var sql = "SELECT * FROM seat";
        try (var connect = PsqlConnectPool.getPool().getConnection();
             var prepStat = connect.prepareStatement(sql)
        ) {
            try (ResultSet it = prepStat.executeQuery()) {
                while (it.next()) {
                    posts.add(new Seat(
                                    it.getInt("id"),
                                    it.getInt("row"),
                                    it.getInt("column"),
                                    it.getInt("hall_id"),
                                    it.getBoolean("status")
                            )
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public void save(Seat seat) {
        if (seat.getId() == 0) {
            create(seat);
        } else {
            update(seat);
        }
    }

    private void create(Seat seat) {
        var sql = "INSERT INTO seat(row, \"column\", hall_id, status) VALUES (?, ?, ?, ?)";
        try (var connect = PsqlConnectPool.getPool().getConnection();
             var prepStat = connect.prepareStatement(sql)
        ) {
            prepStat.setInt(1, seat.getRow());
            prepStat.setInt(2, seat.getColumn());
            prepStat.setInt(3, seat.getHallId());
            prepStat.setBoolean(4, seat.isStatus());
            prepStat.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(Seat seatUpd) {
        var sql = "UPDATE seat SET row=(?), \"column\"=(?), hall_id=(?), status=(?)  WHERE id=(?)";
        try (var connect = PsqlConnectPool.getPool().getConnection();
             var prepStat = connect.prepareStatement(sql)

        ) {
            prepStat.setInt(1, seatUpd.getRow());
            prepStat.setInt(2, seatUpd.getColumn());
            prepStat.setInt(3, seatUpd.getHallId());
            prepStat.setBoolean(4, seatUpd.isStatus());
            prepStat.setInt(5, seatUpd.getId());
            prepStat.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Seat getById(int id) {
        Seat rsl = new Seat(0, 0, 0, 0, false);
        var sql = "SELECT * FROM seat WHERE id=(?)";
        try (var connect = PsqlConnectPool.getPool().getConnection();
             var prepStat = connect.prepareStatement(sql)
        ) {
            prepStat.setInt(1, id);
            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                rsl.setId(resultSet.getInt("id"));
                rsl.setId(resultSet.getInt("row"));
                rsl.setId(resultSet.getInt("column"));
                rsl.setId(resultSet.getInt("hall_id"));
                rsl.setStatus(resultSet.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public void deleteById(int id) {
        var sql = "DELETE FROM seat WHERE id=(?)";
        try (var connect = PsqlConnectPool.getPool().getConnection();
             var prepStat = connect.prepareStatement(sql)
        ) {
            prepStat.setInt(1, id);
            prepStat.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
