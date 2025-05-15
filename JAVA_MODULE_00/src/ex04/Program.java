package ex04;

import java.util.Scanner;

public class Program {
    public  static void counter(String str, long[] arr){
        int n = str.length();
        for (int i =0; i < n; i++){
            int letterIndex = str.charAt(i);
            System.out.println(letterIndex);
            arr[letterIndex] |= ((long)letterIndex << 32);
            arr[letterIndex]++;
            if ((arr[letterIndex] & 0xFFFF) > 999){
                System.err.print("IllegalArgument\n");
                System.exit(-1);
            }

        }
    }

    public static void showData(long[] arr){
        for (long j : arr) {
            if (j > 0) {
                int count = (int)(j & 0xFFFF);
                int letterIndex = (int)(j >> 32);
                System.out.printf("%d:%d\n", letterIndex, count);
            }
        }
    }

    public static void topTen(long[] top, long num){
        long count = (num & 0xFFFF);
        long restoreCount = 0;

        for (int i = 0; i < top.length; i++){
            restoreCount = top[i] & 0xFFFF;
            if (count > restoreCount){
                long tmp = top[i];
                top[i] = num;
                num = tmp;
                count = num & 0xFFFF;
            }
        }
    }

    public static long[] sortData(long[] arr){
        long[] top = new long[10];
        for (int i = 0; i < arr.length; i++){
            if(arr[i] > 0){
                topTen(top, arr[i]);
            }
        }
        return top;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("-> ");
        long[] arr = new long[65535];
        String str = sc.nextLine();
        counter(str,arr);
        showData(arr);
        long[] top = sortData(arr);
        int maxCountRestore = (int)(top[0] & 0xFFFF);
        int maxCount = maxCountRestore > 10 ? 10 : maxCountRestore;

        for(int i = maxCount + 1; i > 0 ; i--){
            for (int j = 0; j < top.length && top[j] != 0; j++){
                int restore = (int)(top[j] & 0xFFFF);
                int percent =  maxCountRestore>10 ? (int)((restore * 10)/maxCountRestore) : restore;
                if (percent == i - 1){
                    System.out.print(restore +"   ");
                }
                if (percent >= i){
                    if (restore >= 10 && restore < 100)
                        System.out.print(" #");
                    else if (restore >= 100)
                        System.out.print("  #");
                    else
                        System.out.print("#");
                    System.out.print("   ");
                }
            }
            System.out.print("\n");

        }
        for (int i = 0; i < top.length && top[i] != 0; i++){
            int restore = (int)(top[i] & 0xFFFF);
            if (restore >= 10 && restore < 100)
                System.out.print(" ");
            else if (restore >= 100)
                System.out.print("  ");
//            else
//                System.out.print("  ");
            System.out.print((char)((top[i]>>32)&0xFFFF) + "   ");
        }
        System.out.print("\n");
    }
}
