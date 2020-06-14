package ru.javanerd;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String args[]) {
        //Создание экземпляра блокировки
        CountDownLatch latch = new CountDownLatch(3);
        System.out.println("Запуск отдельного потока");
        //Создание экземпляра отдельного потока
        new CustomThread(latch);
        try {
            //Ожидание снятия блокировки
            latch.await();
        } catch (InterruptedException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }
        System.out.println("Завершение отдельного потока");
    }
}

//Класс отдельного потока
class CustomThread implements Runnable {
    private final CountDownLatch latch;

    //Конструктор класса
    CustomThread(CountDownLatch latch) {
        this.latch = latch;
        //Запуск потока
        new Thread(this).start();
    }

    //Метод выполнение потока
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            //Уменьшение счетчика срабатывания блокировки
            latch.countDown();
        }
    }
}