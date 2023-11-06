package Singleton.Flights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGenerator {
    public static String generateDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void insertDatesIntoDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Flight-Registration-System", "postgres", "1");

            // Генерируем и вставляем даты в таблицу flights
            String departureDate = generateDate();
            String arrivalDate = generateDate();

            String sql = "INSERT INTO flights (departure_date, arrival_date) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, departureDate);
            preparedStatement.setString(2, arrivalDate);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Даты успешно добавлены в таблицу flights.");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

