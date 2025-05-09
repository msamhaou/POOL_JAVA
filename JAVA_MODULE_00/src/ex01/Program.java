package ex01;

import java.util.Scanner;

public class Program {
    public  static  int sqrt(int num){
        int i = 0;
        for (; i * i<= num; i++){

        }
        return  i;
    }

    public static  void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("-> ");
        int num = sc.nextInt();
        if (num < 2){
            System.err.println("IllegalArgument");
            System.exit(-1); ;
        }
        int j = 1;
        double s = sqrt(num);
        boolean flag = true;
        for (int i =2 ; i < s; i++){
            if (num % i == 0){
                flag = false;
                break;
            }
            j++;
        }
        System.out.printf("%s: %d\n", flag, j);


    }
}
