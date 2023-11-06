package Singleton.Flights;

import java.util.Random;

public class FlightNumberGenerator {

    public static String generateRandomString(int lettersCount, int digitsCount) {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        // Генерируем случайные буквы (большие или маленькие)
        for (int i = 0; i < lettersCount; i++) {
            char letter = (char) (random.nextInt(26) + 'a');
            if (random.nextBoolean()) {
                letter = Character.toUpperCase(letter);
            }
            randomString.append(letter);
        }

        // Генерируем случайные цифры
        for (int i = 0; i < digitsCount; i++) {
            int digit = random.nextInt(10);
            randomString.append(digit);
        }

        // Перемешиваем символы, чтобы получить случайный порядок
        for (int i = randomString.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = randomString.charAt(i);
            randomString.setCharAt(i, randomString.charAt(j));
            randomString.setCharAt(j, temp);
        }

        return randomString.toString();
    }
}
