package ex05;

import javax.management.openmbean.ArrayType;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void Err(String message){
        System.err.println(message);
        System.exit(-1);
    }

    public static int packDayAndHour(int hour, int date, int[] september){
        int DayOfWeekFromSeptember = september[date - 1];
        int packingDateandHour = hour << 8 | (DayOfWeekFromSeptember & 0xFFFF);
        return packingDateandHour;
    }
    public static int getAttendence(Scanner sc, String[] students, int[] classesOfWeek, int[] september) {

        int         counter = 0;
        String[] names = new String[100];
        int[] hours = new int[100];
        int[] dates = new int[100];
        int[] status = new int[100];
        String      line = sc.nextLine();


        while (!line.equals(".")){
            String[] spl = split(line,' ');
            if (spl.length != 4)
                Err("Invalid Input 32");
            names[counter] = spl[0];
            if (!isStudentExist(spl[0], students))
                Err("Student Dont exist");
			hours[counter] = _parseInt(spl[1]);
			dates[counter] = _parseInt((spl[2]));
            int packInputedClass = packDayAndHour(hours[counter], dates[counter], september);
            if (!isClassExist(packInputedClass, classesOfWeek))
                Err("Class Does not Exist");
			status[counter] = decodeStatus(spl[3]);
			//Hours (1,6) && name in names && dates in dates
            line = sc.nextLine();
			counter++;
        }
		return counter;
    }

    static public String[] studentsList(Scanner sc){
        String[] studens = new String[10];
        System.out.print("-> ");
        String line = sc.nextLine();
        int i =0;
        while(!line.equals(".") && i < 10){
            studens[i++] = line;
            if(line.isEmpty() || line.length() > 10 || line.contains(" "))
                Err("Len");
            System.out.print("-> ");
            line = sc.nextLine();
        }
        if (studens[0].isEmpty())
            Err("Empty");
        return studens;
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
//        System.out.println(delimeterCount);
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
    static  public int decodeStatus(String str){
		if (str.equals("HERE"))
			return 1;
		else if (str.equals("NOT_HERE")) {
			return -1;
		}else {
			Err("Invalid Attendence Input");
		}
		return 0;
	}
    static public int _parseInt(String str){
        char[] arr = str.toCharArray();
        int value = 0;
        for (int i = 0; i< arr.length; i++){
            if (arr[i] < '0' || arr[i] > '9')
                Err("Invalid Input: isNotNum");
            value = value * 10 + (arr[i] - '0');
        }
        return value;
    }
    static public boolean isDay(String s){
        String[] strs = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        for (int i = 0; i < strs.length; i++){
            if (s.equals(strs[i]))
                return true;
        }
        return false;
    }

    static public int[] getClassesOfWeek(Scanner sc){
        int[] hours = new int[10];
        int[] days = new int[10];
		int[] results = new int[10];
        int count = 0;
        System.out.print("-> ");
        String line = sc.nextLine();
        while (!line.equals(".") && count < 10){
            String[] spl = split(line, ' ');//spl < 2
            if (spl.length == 0 || spl.length > 2)
                Err("Invalid Date Input");
            hours[count] = _parseInt(spl[0]);
            if (hours[count] < 1 || hours[count] > 6 )
                Err("Invalid Hour");
            days[count] = dayStringToCode(spl[1]);
            if (days[count] == -1)
                Err("Invalid Week Day");
			results[count] = hours[count] << 8 | (days[count]& 0xFFFF);
            System.out.print("-> ");
            line = sc.nextLine();
            count++;
        }
		return results;
    }

	public static int dayStringToCode(String d) {
		if (d.equals("MO")) return 0;
		if (d.equals("TU")) return 1;
		if (d.equals("WE")) return 2;
		if (d.equals("TH")) return 3;
		if (d.equals("FR")) return 4;
		if (d.equals("SA")) return 5;
		if (d.equals("SU")) return 6;
		return -1;
	}
	public static String decodeDay(int dayNum) {
		if (dayNum == 0) return "MO";
		if (dayNum == 1) return "TU";
		if (dayNum == 2) return "WE";
		if (dayNum == 3) return "TH";
		if (dayNum == 4) return "FR";
		if (dayNum == 5) return "SA";
		if (dayNum == 6) return "SU";
		Err("Invalid Day Week Number");
		return null;
	}

	static public int[] generateSeptember(int startDay){
		int [] september = new int[30];
		for (int i =0; i< 30; i++){
			september[i] = (startDay + i) % 7;
		}
		return september;
	}

	static public void checkInputDate(String day){
		int dayCode = dayStringToCode(day);
	}

    static public boolean isStudentExist(String name, String[] students){
        for (int i = 0; i < students.length; i++){
            if(students[i] == null)
                break;
            if( students[i].equals(name))
                return true;
        }
        return false;
    }

    static public boolean isClassExist(int dateAndHour, int[] dates){
        for (int i = 0; i < dates.length; i++){
            if (dates[i] == dateAndHour)
                return true;
        }
        return false;
    }

    static public void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] students = studentsList(sc);
		int[] september = generateSeptember(1);
        int[] classeOfWeek = getClassesOfWeek(sc);
		getAttendence(sc, students, classeOfWeek, september);
		
        sc.close();
    }

}
