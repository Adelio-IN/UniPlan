package com.Adelio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class SelectUser {
    private String name;
    private int id;

    public SelectUser(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract void display();
}

class Student extends SelectUser {
    private String major;

    public Student(String name, int id, String major) {
        super(name, id);
        this.major = major;
    }

    @Override
    public void display() {
        System.out.println("이름: " + getName() + ", ID: " + getId() + ", 전공: " + major);
    }
}

class Professor extends SelectUser
{
    public Professor(String name, int id, String department)
    {
        super(name, id);
    }
    @Override
    public void display()
    {
        System.out.println("이름: " + getName() + ", ID: " + getId());
    }
}
public class UserInput
{
    public static void main(String[] args)
    {
        
    }
}