package com.ycjung.learn.grammar.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) {
        try {
            // 클래스 정보 가져오기
            Class<?> personClass = Class.forName("other.reflection.Person");

            // 생성자 정보 가져오기 및 인스턴스 생성
            Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
            Object personInstance = constructor.newInstance("홍길동", 31);

            // 필드 정보 가져오기 및 값 설정
            Field nameField = personClass.getDeclaredField("name");
            nameField.setAccessible(true); // private 필드 접근 허용
            nameField.set(personInstance, "홍길동2");

            // 메서드 정보 가져오기 및 호출
            Method sayHelloMethod = personClass.getMethod("sayHello");
            sayHelloMethod.invoke(personInstance);

            // private 메서드 호출
            Method secretMethod = personClass.getDeclaredMethod("secretMethod");
            secretMethod.setAccessible(true); // private 메서드 접근 허용
            secretMethod.invoke(personInstance);

            if(personInstance instanceof Person) {
                System.out.println("Person class 입니다..");
                Person person = (Person)personInstance;

                System.out.println(person.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}