package com.example.myapplication1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lists {
    public static List<String> Words = new ArrayList<>();
    public static List<Team> Teams = new ArrayList<>();

    public static int TeamPosInList = 0;

    public static String[] _words = new String[]{"Конкатенация", "Инкремент", "Декремент", "Баг", "Бан",
            "Баян", "Вебинар", "Кряк", "Спам", "Фриланс", "Хорда", "Четная функция", "Биссектриса",
            "Корень", "Вектор", "Диагональ", "Диаметр", "Касательная", "Конус", "Квадрат", "Секущая",
            "Основное тригонометрическое тождество", "Многочлен", "Экстремум", "Дискриминант", "Одночлен",
            "Параллелепипед", "Параллелограмм", "Период", "Планиметрия", "Стереометрия", "Ассиметрия",
            "Производная", "Предел", "Параллельность", "Радикал", "Радиан", "Функция", "Процедура",
            "Взаимнообратность", "Альфа", "Бэтта", "Омега", "Базис", "Линейная комбинация", "Коллиниарность",
            "Критерий", "Нормирование"
    };

    public static void ShuffleWords(){
        Random rnd = new Random();
        for(int i = 0; i < Words.size(); i++) {
            int index = rnd.nextInt(i + 1);
            String word = Words.get(index);
            Words.set(index, Words.get(i));
            Words.set(i, word);
        }
    }
}