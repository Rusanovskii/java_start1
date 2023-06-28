package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collecrions {
    public static void main (String[] args) {
        //  создается массив, количество элементов строко определено
        String[] langs = {"Java", "C#", "Python", "PHP"};
        // также можно создать список, без строгой привязки к размеру, может увеличиваться или уменьшаться
        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");
        // чтобы преобразовать массив в список
        List<String> languages1 = Arrays.asList("Java", "C#", "Python", "PHP");
        // итерация по элементам массива через счетчик: int i = 0; i < langs.length; i++
        // с помощью коллекций
        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
        for (String l : languages1) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
