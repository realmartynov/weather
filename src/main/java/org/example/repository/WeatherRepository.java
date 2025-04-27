package org.example.repository;

import org.example.domain.Weather;

import java.sql.*;

public class WeatherRepository {

    public Weather findByCity(String city) {
        String sql = "SELECT city, temperature, created_at, updated_at, last_access_datetime FROM weather WHERE LOWER(city) = LOWER(?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Weather weather = new Weather(
                        rs.getString("city"),
                        rs.getDouble("temperature")
                );
                // Можно добавить сюда поля created_at и т.д.
                updateLastAccess(conn, city);
                return weather;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении погоды по городу: " + city, e);
        }

        return null;
    }

    public void save(Weather weather) {
        if (exists(weather.getCity())) {
            update(weather);
        } else {
            insert(weather);
        }
    }

    private void insert(Weather weather) {
        String sql = "INSERT INTO weather (city, temperature, created_at, updated_at, last_access_datetime) VALUES (?, ?, now(), now(), now())";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, weather.getCity());
            stmt.setDouble(2, weather.getTemp());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении новой записи о погоде", e);
        }
    }

    private void update(Weather weather) {
        String sql = "UPDATE weather SET temperature = ?, updated_at = now(), last_access_datetime = now() WHERE LOWER(city) = LOWER(?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, weather.getTemp());
            stmt.setString(2, weather.getCity());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении записи о погоде для города: " + weather.getCity(), e);
        }
    }

    public boolean exists(String city) {
        String sql = "SELECT 1 FROM weather WHERE LOWER(city) = LOWER(?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при проверке наличия города в базе: " + city, e);
        }
    }

    private void updateLastAccess(Connection conn, String city) throws SQLException {
        String sql = "UPDATE weather SET last_access_datetime = now() WHERE LOWER(city) = LOWER(?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, city);
            stmt.executeUpdate();
        }
    }
}