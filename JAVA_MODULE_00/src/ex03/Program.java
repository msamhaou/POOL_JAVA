package ex03;

import java.util.Objects;
import java.util.Scanner;

public class Program {
    public static void Err(){
        System.err.print("IllegalArgument\n");
        System.exit(-1);
    }
    public static int DayScan(Scanner sc){
        Scanner intScanner = new Scanner(sc.nextLine());
        int count = 0;
        int min = 2147483647;
        while (intScanner.hasNextInt()) {
            if (intScanner.hasNextInt()) {
                if (count >= 5)
                    Err();
                int i = intScanner.nextInt();
                if (i < 1 || i > 9)
                    Err();
                min = i < min ? i : min;
                count++;
            } else {
                break;
            }
        }
        if (count < 5){
            Err();
        }
        return  min;
    }
    public static  boolean check_week(String line, int week){
        return line.equals("Week " + week);
    }

    public static void drawChart(int week, long restore){
        System.out.print("Week " + week + " ");
        for (int i = 0; i < restore; i++){
            System.out.print('=');
        }
        System.out.println('>');

    }
    public  static void representData(int shift, long stockOne, long stockTwo){
        long restore = 0;
        for (int i =0; i < shift; i++){
            if (i < 16){
                restore = (stockOne >> (i * 4)) & 0xF;
            }else {
                restore = (stockTwo >> ((i - 16) * 4)) & 0xF;
            }
            drawChart(i + 1,restore);
        }
    }
    public  static  long stockNumbers(long stock, int num, int shift){
        stock |= ((long)(num & 0xF)) << (shift * 4);;
        return stock;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long stock1 = 0;
        long stock2 = 0;
        int week = 1;
        int shift = 0;
        int i = 0;
        while ( shift < 18)
        {
            System.out.print("-> ");
            if (i % 2 == 0){
                String line = sc.nextLine();
                if (line.equals("42"))
                    break;
                if (!check_week(line,week)){
                    Err();
                }
                week++;
            }else {
                int min = DayScan(sc);
                if (shift < 16) {
                    stock1 = stockNumbers(stock1, min, shift);
                }
                else
                    stock2 = stockNumbers(stock2,min,shift);
                shift++;
            }
            i++;
        }
        representData(shift,stock1,stock2);
    }
}
