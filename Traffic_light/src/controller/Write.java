package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Write {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static int getMinuteRed() {
        return minuteRed;
    }

    public static int getMinuteGreen() {
        return minuteGreen;
    }

    public static int getMinuteYellow() {
        return minuteYellow;
    }

    private static void setMinuteRed(int minuteRed) {
        Write.minuteRed = minuteRed;
    }

    private static void setMinuteGreen(int minuteGreen) {
        Write.minuteGreen = minuteGreen;
    }

    private static void setMinuteYellow(int minuteYellow) {
        Write.minuteYellow = minuteYellow;
    }
    // по умолчанию время горения каждого цвета равен нулу.
    // данные проинициализированы чтобы можно было проверить заполненны ли они в дальнейшем
    private static int minuteRed = 0;
    private static int minuteGreen = 0;
    private static int minuteYellow = 0;

    //регулярное выражение для проверки ввода данных
    private static String pattern1 = "[1-9]";
    private static Pattern p1 = Pattern.compile(pattern1);

    // метод который будут вызывать сеттеры цветов светофора чтобы проинициализировать значения новыми данными
    private static int entryIsChecked(String name) throws IOException {
        int minute=0;
        System.out.println("please write minute for color " + name + " only numeric");
        String s = bufferedReader.readLine();
        Matcher m = p1.matcher(s);
        if (m.find()) {
            System.out.println("ok");
            minute = Integer.valueOf(s);
        } else System.out.println("no!");
        return minute;
    }

    //метод записи, если все цвета теперь имеют свое время горения больше нуля то метод отработал
    public static boolean writeMinute() throws IOException {
        for (; ; ) {
            if (minuteRed == 0) {
                setMinuteRed(entryIsChecked("RED"));
            }

            if (minuteYellow == 0) {
                setMinuteYellow(entryIsChecked("YELLOW"));
            }

            if (minuteGreen == 0) {
                setMinuteGreen(entryIsChecked("GREEN"));
            }

            if (minuteRed != 0 && minuteGreen != 0 && minuteYellow != 0) {
                bufferedReader.close();
                System.out.println("all right");
                return true;
            }
        }
    }
}
