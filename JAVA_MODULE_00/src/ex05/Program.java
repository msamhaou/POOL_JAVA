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
        return DayOfWeekFromSeptember << 16 | (hour & 0xFFFF);
    }

    public static int getStudentIndex(String name, String[] students){
        for (int i = 0; i < students.length; i++){
            if (students[i] != null && students[i].equals(name))
                return i;
        }
        return -1;
    }

    public static boolean isInputExist(String line, String[] storedInputs){
        for (int i =0 ; i < storedInputs.length; i++){
            if (storedInputs[i] == null)
                break;
            if (storedInputs[i].equals(line))
                return true;
        }
        return false;
    }
    public static int[] getAttendence(Scanner sc, String[] students, int[] classesOfWeek, int[] september) {

        int         counter = 0;
        String[] names = new String[100];
        int[] hours = new int[100];
        int[] dates = new int[100];
        int[] status = new int[100];
        int[] results = new int[100];
        String[] storeInput = new String[100];

        System.out.print("-> ");
        String      line = sc.nextLine();

        while (!line.equals(".")){
            if (isInputExist(line, storeInput))
                Err("Do not repeat inputs");
            String[] spl = split(line,' ');
            if (spl.length != 4)
                Err("Invalid Input 32");
            names[counter] = spl[0];
            int studentIndex = getStudentIndex(names[counter], students);
            if (studentIndex == -1)
                Err("Student Do not exist");
			hours[counter] = _parseInt(spl[1]);
			dates[counter] = _parseInt((spl[2]));
            int packInputedClass = packDayAndHour(hours[counter], dates[counter], september);
            if (!isClassExist(packInputedClass, classesOfWeek))
                Err("Class Does not Exist");
			status[counter] = decodeStatus(spl[3]);
            results[counter] = (studentIndex << 24 ) | (status[counter] << 16 ) |  (dates[counter]  << 8 ) | (hours[counter] & 0xFF);
            storeInput[counter] = line;
            counter++;
			//Hours (1,6) && name in names && dates in dates
            System.out.print("-> ");
            line = sc.nextLine();
        }
		return results;
    }

    public static int[][] studentAttendence(Scanner sc, String[] students, int[] classesOfWeek, int[] september) {
        int counter = 0;
        int[][] studentsData = new int[10][30*6];
        String[] storeInput = new String[100];
        int[] hours = new int[100];
        int[] dates = new int[100];
        int[] status = new int[100];
        int[] results = new int[100];
        String[] names = new String[100];
        int[] indexCount = new int[10];
        System.out.print("-> ");
        String      line = sc.nextLine();

        while (!line.equals(".")) {
            if (isInputExist(line, storeInput))
                Err("Do not repeat inputs");
            String[] spl = split(line,' ');
            if (spl.length != 4)
                Err("Invalid Input 32");
            names[counter] = spl[0];
            int studentIndex = getStudentIndex(names[counter], students);
            if (studentIndex == -1)
                Err("Student Do not exist");
            hours[counter] = _parseInt(spl[1]);
            dates[counter] = _parseInt((spl[2]));
            int packInputedClass = packDayAndHour(hours[counter], dates[counter], september);
            if (!isClassExist(packInputedClass, classesOfWeek))
                Err("Class Does not Exist");
            status[counter] = decodeStatus(spl[3]);
            System.out.println("d:" + (dates[counter] - 1) + ", h:" + hours[counter]);
            studentsData[studentIndex][6 * (dates[counter] - 1) + hours[counter]] = status[counter];
            indexCount[studentIndex]++;
            System.out.print("-> ");
            line = sc.nextLine();
        }
        return studentsData;
    }

    static  public boolean isAlphabet(char c){
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    static public boolean isAllAlphabet(String name){
        char[] nameToChar = name.toCharArray();

        for (int i = 0; i < nameToChar.length; i++){
            if (!isAlphabet(nameToChar[i]))
                return false;
        }
        return true;
    }

    static public String[] studentsList(Scanner sc){
        String[] studens = new String[10];
        System.out.print("-> ");
        String line = sc.nextLine();
        int i =0;
        while(!line.equals(".") ){
            if (i >= 10)
                Err("Invalid Input: More than 10 students");
            if (isStudentExist(line, studens))
                Err("Student Name Already Exist");
            studens[i++] = line;
            if (!isAllAlphabet(line))
                Err("Invalid input: Student Name");
            if(line.isEmpty() || line.length() > 10 || line.contains(" "))
                Err("Len > 10");
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
			return 2;
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
        while (!line.equals(".") ){
            if (count >= 10)
                Err("Invalid Input: More than 10 Classes inserted");
            String[] spl = split(line, ' ');//spl < 2
            if ( spl.length != 2)
                Err("Invalid Date Input");
            hours[count] = _parseInt(spl[0]);
            if (hours[count] < 1 || hours[count] > 6 )
                Err("Invalid Hour");
            days[count] = dayStringToCode(spl[1]);
            if (days[count] == -1)
                Err("Invalid Week Day");
			results[count] = days[count] << 16 | (hours[count]& 0xFFFF);
            System.out.print("-> ");
            line = sc.nextLine();
            count++;
        }
        int[] finalResult = new int[count];
        for (int i = 0; i < count; i++){
            finalResult[i] = results[i];
        }
		return finalResult;
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

    static public int isDayClassExist(int[] classesWeek, int entredDay){
        for (int i = 0; i < classesWeek.length; i++){
            if (classesWeek[i] == 0)
                continue;
            if ((classesWeek[i] >> 16) == entredDay)
                return i;
        }
        return -1;
    }

    static private void insertionSort(int arr[], int n){
        for (int i = 1; i < n; ++i){
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static public void sortClasses(int[] classesOfWeek){
        insertionSort(classesOfWeek, classesOfWeek.length);
    }

    static private void debugArr(int[] arr){
        for (int i = 0 ;i < arr.length; i++){
            if (arr[i] > 0)
                System.out.println(decodeDay((arr[i] >> 16)) + " " + (arr[i] & 0xFFFF));
        }
    }

    static public int[] createCopy(int[] arr, int length){
        int[] copy = new int[length];
        for (int i = 0; i < length; i++){
            copy[i] = arr[i];
        }
        return copy;
    }
    static public void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] students = studentsList(sc);
		int[] september = generateSeptember(1);
        int[] classeOfWeek = getClassesOfWeek(sc);
        sortClasses(classeOfWeek);
		int[][] attendeceArray = studentAttendence(sc, students, classeOfWeek, september);

        /// *************** ///
        System.out.print("            ");
        for (int i = 0; i < 30; i++){
            int j = isDayClassExist(classeOfWeek, september[i]);
            while (j != -1&& j < classeOfWeek.length && (classeOfWeek[j] >> 16) == september[i]){
                System.out.print((classeOfWeek[j] & 0xFFFF) + ":00 " + decodeDay(classeOfWeek[j] >> 16) + " " + (i + 1) + " | ");
                j++;
            }

        }
        System.out.println("");
        /// *************** ///
        for (int i = 0; i < attendeceArray.length; i++){
            int[] studentData = attendeceArray[i];
            if (students[i] == null)
                break;
            System.out.print(students[i] + "      |");
            for (int j = 0; j < 30; j++){
                for (int k = 0; k < 6; k++){
                    if (isClassExist(packDayAndHour(k , j + 1, september), classeOfWeek)){
                        if (studentData[(j * 6)+k] != 0){
                            System.out.print("          "+studentData[(j * 6)+ k]+"|");
                        }
                        else
                            System.out.print("           |");
                    }

                }
            }
            System.out.println("");
        }


		
        sc.close();
    }

}
