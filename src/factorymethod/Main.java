package factorymethod;

import factorymethod.abstractmethod.*;

public class Main {

    public static void main(String[] args) {
//        Moveable car = new Car();
//        car.go();
//        Moveable plane = new Plane();
//        plane.go();
//        Moveable car = new CarFactory().create();
//        car.go();

        //抽象
//        Car car = new Car();
//        car.go();
//        AK47 ak47 = new AK47();
//        ak47.shoot();
//        Bread bread = new Bread();
//        bread.printName();

//        AbstractFactory af = new ModernFactory();
        AbstractFactory af = new MagicFactory();
        Vehicle car = af.createVehicle();
        car.go();
        Weapon ak47 = af.createWeapon();
        ak47.shoot();
        Food bread = af.createFood();
        bread.printName();
    }
}
