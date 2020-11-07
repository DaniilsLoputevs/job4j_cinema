package ru.job4j.cinema.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class PsqlConnectPool implements AutoCloseable {
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlConnectPool() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader(getClass().getClassLoader()
                        .getResource("db.properties").getFile())
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    @Override
    public void close() throws IOException {
        try {
            pool.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static final class Lazy {
        private static final PsqlConnectPool INST = new PsqlConnectPool();
    }

    public static BasicDataSource getPool() {
        return Lazy.INST.pool;
    }
}

