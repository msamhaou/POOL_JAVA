package ex00;

public class Program {
    public static int sum(int num){
        int res = 0;
        if (num <= 0)
            return 0;
        res += sum(num/10) + num % 10;
        return res;
    }
    public static void main(String[] args){
        int digists = 123456;
        int res = sum(digists);
        System.out.println(res);
    }
}
