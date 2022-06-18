package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    // private static final char exitCharacter = '!';
    public static boolean flag, flag2 = false;
    public static int number1;
    public static int number2;
    public static char operation;
    public static int result;

    public static void main(String[] args) {
        System.out.println("Введите выражение, состоящее из двух целых чисел от 0 до 10, и знака операции (напр. 2+2): ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        calc(text);

    }

    public static String calc(String input) {
        // String result;
        String[] roman = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};

        try {
            String[] blocks = input.split("[+-/*]");
            if (blocks.length < 2) {
                throw new MyException();
            }
            // проверяем, входит ли строка blocks[0] в массив строк массива "roman"
            for (int i = 0; i < roman.length; i++) {
                if (roman[i].equals(blocks[0])) {
                    flag = true;
                }
            }
            for (int i = 0; i < roman.length; i++) {
                if (roman[i].equals(blocks[1])) {
                    flag2 = true;
                }
            }
            if (blocks.length > 2) {
                throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

            if (flag != flag2) {
                throw new IOException("т.к. используются одновременно разные системы счисления");
            }
            //flag = true, значит будем иметь дело с римскими обозначениями
            if (flag && flag2) {
                number1 = ToNumber.romanToNumber(blocks[0]);
                number2 = ToNumber.romanToNumber(blocks[1]);
            } else {
                number1 = Integer.parseInt(blocks[0]);
                number2 = Integer.parseInt(blocks[1]);
            }
            operation = input.charAt(blocks[0].length());
            result = Calculator.calculate(getVar1(), getVar2(), getOper());
            if (flag == false && flag2 == false) {
                System.out.println(result);
            }
            if ((number1 > 10 || number1 < 0) || (number2 > 10 || number2 < 0)) {
                throw new IllegalArgumentException();
            }

        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Неверный формат данных");
        } catch (IOException e) {
            throw new IllegalArgumentException("throws Exception т.к. используются одновременно разные системы счисления");
        } catch (MyException e) {
            throw new IllegalArgumentException("throws Exception т.к. строка не является математической операцией");
        } catch (Exception e) {
            throw new IllegalArgumentException("throws Exception т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (getFlag() && getFlag2()) {
            if (result < 0) {
//                try {
//                    throw new IOException();
//                } catch (IOException e) {
                System.err.println("throws Exception т.к. в римской системе нет отрицательных чисел");
                System.exit(0);
            } else if (result == 0) {
                System.err.println("throws Exception т.к. результат работы равен нулю");
                System.exit(0);
            }
            // break;
            // }
            System.out.println(ToRoman.intToRoman(result));

        } /*else {
            return String.valueOf(result);
        }*/
        return String.valueOf(result);
    }

    public static int getVar1() {
        return number1;
    }

    public static int getVar2() {
        return number2;
    }

    public static char getOper() {
        return operation;
    }

    public static boolean getFlag() {
        return flag;
    }

    public static boolean getFlag2() {
        return flag2;
    }

    public static int getResult() {
        return result;
    }
}

