package com.company;

import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String secretKey = "123";

        while (true) {
            System.out.print("Please enter a key: ");
            String key = scanner.nextLine();

            if (key.equals(secretKey)) {
                System.out.println("To encrypt your message press 1");
                System.out.println("To decrypt your message press 2");
                System.out.print("Enter your option: ");

                int choice = scanner.nextInt();
                // This nextLine() prevents buffer from consuming "end of line" from the first input
                // Without it String code bellow would have value of ' '
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Please enter a message to encrypt: ");
                        String code = scanner.nextLine();
                        String result = encryptMessage(code);
                        System.out.println("Encrypted Code is: " + result);
                        break;
                    case 2:
                        System.out.print("Please enter a message to decrypt: ");
                        String codeTwo = scanner.nextLine();
                        String resultTwo = decryptMessage(codeTwo);
                        System.out.println("Decrypted code is: " + resultTwo);
                        break;
                }
            } else {
                System.out.println("Key is not valid");
            }
        }
    }

    public static String encryptMessage(String code) {

        char[] characters = code.toCharArray();
        char[] swap = new char[code.length()];

        for (int i = 0; i < code.length(); i++) {
            int ascii = characters[i];
            ascii += 1;

            switch (ascii) {
                // If there is blank space ' ' don't convert it to '{' which is next in ASCII table
                case 33:
                    swap[i] = ' ';
                // Preventing 'Z' to go to '[' value
                case 91:
                    swap[i] = 'A';
                // Preventing 'z' to go to '{' value
                case 173:
                    swap[i] = 'z';
                default:
                    swap[i] = (char) ascii;
            }
        }
        return String.valueOf(swap);
    }

    public static String decryptMessage(String code) {

        char[] characters = code.toCharArray();
        char[] swap = new char[code.length()];

        for (int i = 0; i < code.length(); i++) {
            int ascii = characters[i];
            ascii -= 1;

            switch (ascii) {
                // If there is blank space ' ' don't convert it to '[' which is one char behind in ASCII table
                case 31:
                    swap[i] = ' ';
               // Preventing 'A' to go to '@' value
                case 65:
                    swap[i] = 'Z';
                default:
                    swap[i] = (char) ascii;
            }
        }
        return String.valueOf(swap);
    }
}

