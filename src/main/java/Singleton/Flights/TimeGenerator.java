package Singleton.Flights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TimeGenerator {
    public static String generateTime() {
        // Генерация времени в формате "HH:mm:ss"
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date time = new Date(); // Получаем текущее время
        return timeFormat.format(time);
    }

    public static void insertTimesIntoDatabase() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Flight-Registration-System", "postgres", "1");

            // Генерируем и вставляем времена в таблицу flights
            String departureTime = generateTime();
            String arrivalTime = generateTime();

            String sql = "INSERT INTO flights (departure_time, arrival_time) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, departureTime);
            preparedStatement.setString(2, arrivalTime);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Времена успешно добавлены в таблицу flights.");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertTimesIntoDatabase();
    }
}

