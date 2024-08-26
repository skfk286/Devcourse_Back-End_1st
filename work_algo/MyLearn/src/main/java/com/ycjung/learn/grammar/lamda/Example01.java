package com.ycjung.learn.grammar.lamda;

public class Example01 {
    public static void action(Calculable calculable) {
        int x = 10;
        int y = 4;

        calculable.calculate(x, y);
    }


    public static void main(String[] args) {
        action(new Calculable() {
            @Override
            public void calculate(int x, int y) {
                int result = x + y;
                System.out.println("result : " + result);
            }
        });

        action(new Calculable() {
            @Override
            public void calculate(int x, int y) {
                int result = x - y;
                System.out.println("result : " + result);
            }
        });

        Person person = new Person();
        person.action(() -> {
            System.out.println("출근을 합니다.");
            System.out.println("프로그래밍을 합니다.");
        });

        person.action(() -> System.out.println("퇴근을 합니다."));

        person.action(Computer :: staticMethod);

        Computer computer = new Computer();

        person.action(computer :: instanceMethod);
    }
}

class Computer {
    public static double staticMethod(double x, double y) {
        return x + y;
    }

    public double instanceMethod(double x, double y) {
        return x * y;
    }
}

class Person {
    public void action(Workable workable) {
        workable.work();
    }

    public void action(Calculable2 calculable) {
        double result = calculable.calculate(10, 4);
        System.out.println(result);
    }
}

@FunctionalInterface
interface Calculable {
    void calculate(int x, int y);
}

@FunctionalInterface
interface Calculable2 {
    double calculate(double x, double y);
}

// 매개 변수가 없는 람다식
@FunctionalInterface
interface Workable {
    void work();
}
