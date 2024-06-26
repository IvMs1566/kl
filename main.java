

import java.util.HashMap;

    class Main {
        public static String calc(String input) {
            String[] parts = input.split(" ");

            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid input format. Please provide two numbers and an operator separated by spaces.");
            }

            String romanPattern = "^[IVXLCDM]+$";
            String operator = parts[1];
            int num1, num2;

            // Check if input numbers are valid (between 1 and 10)
            try {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[2]);

                if (num1 < 1 num1 > 10 num2 < 1 || num2 > 10){
                    throw new IllegalArgumentException("Input numbers must be between 1 and 10.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input numbers must be valid integers.");
            }

            // Check if input numbers are both Roman numerals or both Arabic numerals
            boolean isRomanNumeral1 = parts[0].matches(romanPattern);
            boolean isRomanNumeral2 = parts[2].matches(romanPattern);

            if (isRomanNumeral1 != isRomanNumeral2) {
                throw new IllegalArgumentException("Cannot mix Roman and Arabic numerals in the same expression.");
            }

            if (isRomanNumeral1) {
                int result = evaluateRoman(num1, num2, operator);
                return toRoman(result);
            } else {
                int result = evaluateArabic(num1, num2, operator);
                return String.valueOf(result);
            }
        }

        private static int evaluateArabic(int num1, int num2, String operator) {
            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 == 0) {
                        throw new IllegalArgumentException("Division by zero is not allowed.");
                    }
                    return num1 / num2;
                default:
                    throw new IllegalArgumentException("Invalid operator. Please use +, -, *, /.");
            }
        }

        private static int evaluateRoman(int num1, int num2, String operator) {
            HashMap<Character, Integer> romanToInt = new HashMap<>();
            romanToInt.put('I', 1);
            romanToInt.put('V', 5);
            romanToInt.put('X', 10);
            romanToInt.put('L', 50);
            romanToInt.put('C', 100);
            romanToInt.put('D', 500);
            romanToInt.put('M', 1000);

            int val1 = romanToInt.get(Character.toUpperCase(num1));
            int val2 = romanToInt.get(Character.toUpperCase(num2));

            switch (operator) {
                case "+":
                    return val1 + val2;
                case "-":
                    return val1 - val2;
                case "*":
                    return val1 * val2;
                case "/":
                    if (val2 == 0) {
                        throw new IllegalArgumentException("Division by zero is not allowed.");
                    }
                    return val1 / val2;
                default:
                    throw new IllegalArgumentException("Invalid operator. Please use +, -, *, /.");
            }
        }

        private static String toRoman(int num) {
            if (num < 1)
                throw new IllegalArgumentException("Roman numerals cannot represent zero or negative numbers.");
            if (num > 3999) throw new IllegalArgumentException("Roman numerals are limited to numbers up to 3999.");

            String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            int[] romanValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            StringBuilder romanNumeral = new StringBuilder();


            int i = 0;
            while (num > 0) {
                if (num - romanValues[i] >= 0) {
                    romanNumeral.append(romanSymbols[i]);
                    num -= romanValues[i];
                } else {
                    i++;
                }
            }

            return romanNumeral.toString();
        }
    }




