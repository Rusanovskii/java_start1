package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance(){
        Point p1 = new Point(5.0,8.0);
        Point p2 = new Point(8.0,5.0);
        Assert.assertEquals(p1.distance(p2),4.242640687119285, "Расчет выполнен не верно");
        Assert.assertTrue(p1.distance(p2) == 4.242640687119285,"Произведена ошибка в вычислении");
    }
    @Test
    public void testDistance2(){
        Point p1 = new Point(0.0,8.0);
        Point p2 = new Point(8.0,0.0);
        Assert.assertEquals(p1.distance(p2),11.313708498984761, "Расчет выполнен не верно");
    }
    @Test
    public void testIncorrect(){
        Point p1 = new Point(-5.0,8.0);
        Point p2 = new Point(8.0,-5.0);
        Assert.assertEquals(p1.distance(p2),18.384776310850235, "Расчет выполнен не верно");
        Assert.assertTrue(p1.distance(p2) == 18.384776310850235,"Произведена ошибка в вычислении");
    }
}
