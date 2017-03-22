package module;

import view.LightWindow;

import java.io.IOException;

import static controller.Write.writeMinute;

public class ProcessTraffic {
    public static void go() throws IOException, InterruptedException {
        writeMinute();
        // запуск контроллера, контроллер осуществляет ввод информации с клавиатуры,
        // пока не будет введлено все верно
        if (writeMinute())
            new LightWindow().init();
        // если метод ввода отработал коректно, то создание новой переменной у класса прорисовки окна и
        // вызов у него метода прорисовки окна для пользователя
    }
}
