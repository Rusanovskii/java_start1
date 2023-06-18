package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point(5.0,8.0);
        Point p2 = new Point(8.0,5.0);

        System.out.println("Вычисляем расстояния между p1:" + p1.x + "," + p1.y + " и p2:" + p2.x + "," + p2.y + " = " + p1.distance(p2));

        /*
        // Передаем значение для переменной any указывая его в парпметре функции
        hello("World");
        hello("Mr Barantsev");
        hello("Java");
        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());
        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
        */
    }

    /*
    public static void hello(String any) {
        // Указываем, в параметре используемой в main функции, что any это строка
        System.out.println("Hello, "  + any + "!");
    }
    */
}



