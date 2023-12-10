package com.phatdo.Cryptography;

import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static String generatePassword(int length) {
        char[] asciiCharacters = new char[95];
        for (int i = 0; i < 95; i++) {
            asciiCharacters[i] = (char) (i + 32);
        }

        StringBuilder randomPassword = new StringBuilder();

        try {
            if (length < 8 || length > 20) {
                throw new IllegalArgumentException();
            }

            Random random = new Random();
            for (int i = 0; i < length; i++) {
                char randomCharacter = asciiCharacters[random.nextInt(95)];
                randomPassword.append(randomCharacter);
            }

            return randomPassword.toString();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid length");
            return generatePassword(getValidLength());
        }
    }

    private static int getValidLength() {
        try (Scanner scanner = new Scanner(System.in)) {
            int length;
            do {
                System.out.print("Enter password length (8-20): ");
                length = scanner.nextInt();
            } while (length < 8 || length > 20);
            return length;
        }
    }

    public static void main(String[] args) {
        int length = getValidLength();
        String password = generatePassword(length);
        System.out.println("Generated Password: " + password);
    }
}
