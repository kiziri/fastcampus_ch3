package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car{}
class SuperCar extends Car {}
class Truck extends Car {}
class Engine {}

public class Main1 {
    public static void main(String[] args) throws Exception {
        Car car = new SuperCar();
        Car car1 = getCar();
        Engine engine = (Engine) getObject("engine");
        System.out.println("car = " + car);
        System.out.println("car = " + car1);
        System.out.println("car = " + engine);
    }

    // key를 입력받아 해당하는 value의 객채를 생성하도록함
    static Object getObject(String key) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty(key));

        return clazz.newInstance();
    }

    static Car getCar() throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty("car"));

        return (Car)clazz.newInstance();
    }
}
