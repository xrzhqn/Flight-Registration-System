package Singleton.Flights;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Flights {
    private static Flights flights;
    private static String flightInfo = "\n";

    public static synchronized Flights getFlights() {
        if(flights == null) {
            flights = new Flights();
        }
        return flights;
    }

    private Flights() {}

    public void addFlight() {

        System.out.println("Так как проект не крупный, полет будет создаваться через рандомайзер...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Flight-Registration-System", "postgres", "1");

            String sql = "INSERT INTO flights (flight_number, from_city, to_city, departure_date, departure_time, arrival_date, arrival_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            String flightNumber = FlightNumberGenerator.generateRandomString(3, 3);
            String fromCity = CityGenerator.generateCity();
            String toCity = CityGenerator.generateCity();
            String departureDate = DateGenerator.generateDate();
            String departureTime = TimeGenerator.generateTime();
            String arrivalDate = DateGenerator.generateDate();
            String arrivalTime = TimeGenerator.generateTime();

            System.out.println("\nFlight has been added successfully.\n");

            preparedStatement.setString(1, flightNumber);
            preparedStatement.setString(2, fromCity);
            preparedStatement.setString(3, toCity);
            preparedStatement.setString(4, departureDate);
            preparedStatement.setString(5, departureTime);
            preparedStatement.setString(6, arrivalDate);
            preparedStatement.setString(7, arrivalTime);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showFlights() {
        try{
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Flight-Registration-System", "postgres", "1");

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM flights";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                System.out.println("Flight Nummber|| From City || To City || Departure Date || Departure Time || Arrival Date || Arrival Time||");

                String flightNumber = resultSet.getString("flight_number");
                String fromCity = resultSet.getString("from_city");
                String toCity = resultSet.getString("to_city");
                java.sql.Date departureDate = resultSet.getDate("departure_date");
                java.sql.Time departureTime = resultSet.getTime("departure_time");
                java.sql.Date arrivalDate = resultSet.getDate("arrival_date");
                java.sql.Time arrivalTime = resultSet.getTime("arrival_time");

                System.out.printf("%s\t\t\t%s\t\t%s \t%s\t%s\t\t%s\t\t%s%n",
                        flightNumber, fromCity, toCity, departureDate, departureTime, arrivalDate, arrivalTime);
            }


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
