package com.Adelio;

import com.Adelio.Sort.LectureSort;
import com.Adelio.Sort.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("강의 수를 입력하세요.");
        int count = sc.nextInt();
        sc.nextLine();

        String[] Lecture_Name = new String[count];

        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "번째 강의명을 입력하세요: ");
            Lecture_Name[i] = sc.nextLine();
        }

        String[] Lecture_Day = new String[count];

        for (int i = 0; i < count; i++)
        {
            System.out.print((i + 1) + "강의 요일을 입력하세요. Ex: 월, 화 ");
            Lecture_Day[i] = sc.nextLine();
        }

        int[] Lecture_Time = new int[count];
        for (int i = 0; i < count; i++)
        {
            System.out.print((i + 1) + "강의 시간을 입력하세요. Ex: 10, 13");
            Lecture_Time[i] = sc.nextInt();
            sc.nextLine();
        }

        List<Sort> Lectures = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            Lectures.add(new Sort(Lecture_Name[i], Lecture_Day[i], Lecture_Time[i]));
        }
        LectureSort.sort_Day_Time(Lectures);

        System.out.println("강의 목록");
        for (Sort lecture : Lectures)
        {
            int i = count; i++;
            System.out.println(Lecture_Name[i] + " " + Lecture_Day[i] + "요일 " + Lecture_Time[i] + "시");
        }
    }
}