package Singleton.Flights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class CityGenerator {
    private static final String[] cities = {
            "Алматы", "Нур-Султан", "Шымкент", "Караганда", "Актобе", "Тараз", "Павлодар", "Орал", "Семей", "Атырау",
            "Костанай", "Уральск", "Петропавловск", "Талдыкорган", "Кокшетау", "Актау", "Атырау", "Аксу", "Жезказган",
            "Кызылорда", "Туркестан", "Капшагай", "Экибастуз", "Риддер", "Жанаозен", "Кентау", "Аксай", "Жаркент", "Аягөз"
    };

    public static String generateCity() {
        // Генерация случайного города из массива
        Random random = new Random();
        int index = random.nextInt(cities.length);
        return cities[index];
    }

    public static void insertCitiesIntoDatabase() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Flight-Registration-System", "postgres", "1");

            // Генерируем и вставляем города в таблицу flights
            String fromCity = generateCity();
            String toCity = generateCity();

            String sql = "INSERT INTO flights (from_city, to_city) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fromCity);
            preparedStatement.setString(2, toCity);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Города успешно добавлены в таблицу flights.");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

