package ex02;

import java.util.Scanner;

public class Program {
    public static int sum(int num){
        int res = 0;
        if (num <= 0)
            return 0;
        res +=  num % 10 + sum(num/10);
        return res;
    }

    public  static  int sqrt(int num)
    {
        int i = 0;
        for (; i * i<= num; i++){
            //empty
            continue;
        }
        return  i;
    }

    public  static int isPrime(int num){
        if (num < 2){
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        double s = sqrt(num);
        int flag = 1;
        for (int i =2 ; i < s; i++){
            if (num % i == 0){
                flag = 0;
                break;
            }
        }
        return flag;
    }

    public static  void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int l = -1;
        int num = 0;
        int res = 0;
        while (true){
            System.out.print("-> ");
            if (!sc.hasNextInt()){
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            num = sc.nextInt();
            if (num == 42){
                break;
            }
            int s = sum(num);
            res += isPrime(s);
        }
        System.out.printf("Count of coffee-request - %d", res);

    }
}
