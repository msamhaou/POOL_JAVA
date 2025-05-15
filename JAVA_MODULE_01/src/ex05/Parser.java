package ex05;

public class Parser {
    static class AlphabetNotAccepted extends RuntimeException{
        AlphabetNotAccepted(String message){
                super(message);
            }
    }

    static public int parseInt(String str){
        char[] arr = str.toCharArray();
        int value = 0;
        for (int i = 0; i< arr.length; i++){
            if (arr[i] < '0' || arr[i] > '9')
                throw new AlphabetNotAccepted("Alphabet are Not accepted");
            value = value * 10 + (arr[i] - '0');
        }
        return value;
    }

    static public String[] split(String str, char delim){
        int delimeterCount = 0;
        char[] arr = str.toCharArray();
        for (int i =0 ; i< arr.length; i++){
            while (i < arr.length && arr[i] == delim)
                i++;
            if (i >= arr.length)
                break;
            if (arr[i] != delim){
                delimeterCount++;
                while (i < arr.length && arr[i]!= delim)
                    i++;
            }

        }
        String[] res = new String[delimeterCount];
        int words = 0;
        for (int i =0 ; i< arr.length; i++){
            while (i < arr.length && arr[i] == delim)
                i++;
            if (i >= arr.length)
                break;
            if (arr[i] != delim){
                int start = i;
                while (i < arr.length && arr[i]!= delim){
                    i++;
                }
                res[words++] = new String(arr, start, i - start);
            }

        }
        return res;
    }

}
