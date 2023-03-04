import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 *
 * @author NongGuitar
 */

public class Hogwarts_emergency {
    //"Gryffindor" = 0, "Hufflepuff" = 1 , "Ravenclaw" = 2 , "Slytherin" =3 
    private static String[] houseList = { "Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin" }; //รายชื่อบ้าน เอาไว้เช็คค่า,แสดงชื่อบ้าน
    private static int NUM; // เก็บจำนวนนักเรียน
    private static List<String> studentNames; // เก็บรายชื่อนักเรียน
    private static Map<String, List<String>> member_inHouses; // เก็บรายชื่อนักเรียนที่อยู่ในแต่ละบ้าน และ คัดสรรบ้าน ให้นักเรียน 
    private static Map<String, Integer> counts_inHouses; // เก็บจำนวนนักเรียนในแต่ละบ้าน


    public Hogwarts_emergency(int N) {
        this.NUM = N;
        this.studentNames = new ArrayList<>();
        this.member_inHouses = new TreeMap<>();
        this.counts_inHouses = new TreeMap<>();
        
        //Create map ให้ว่างเอาไว้
        for (String house : houseList) {
            counts_inHouses.put(house, 0);
            member_inHouses.put(house, new ArrayList<>());
        }
    }

    //หมวกคัดสรร
    public static void TheSorting_Hat(String name) { //รับชื่อนักเรียนมา
        
            //หาตำแหน่งของบ้าน จำนวนนักเรียน % จำนวนบ้าน ([ex.] 5%4 = 1 || 5%4 = "Hufflepuff")
            String houseName = houseList[studentNames.size() % houseList.length];   

            studentNames.add(name);//add ชื่อนักเรียน ลง ใน array
            counts_inHouses.put(houseName, counts_inHouses.get(houseName) + 1); // เก็บจำนวนนักเรียนในแต่ละบ้าน counts_inHouses.put(ชื่อบ้าน, counts_inHouses.get(ชื่อบ้าน) + 1);
            member_inHouses.get(houseName).add(name); // คัดสรรนักเรียนให้อยู่ในแต่ละบ้าน member_inHouses.get(ชื่อบ้าน).add(ชื่อนักเรียน);
            System.out.println('"'+name+'"'+ " " + houseName + " houses"); //พิมพ์ชื่อนักเรียน + ชื่อบ้าน ออกมา
         
            //จำนวนนักเรียนที่คัดสรร เท่ากับ จำนวนนักเรียนที่เปิดรับ (สรุปจำนวนคนในแต่ละบ้าน นักเรียนที่อยู่ในแต่ละบ้าน)
         if (studentNames.size() == NUM) { 
            for (int i = 0; i < houseList.length; ++i) {  
                System.out.println(houseList[i].toString() + " houses : " + counts_inHouses.get(houseList[i]) + " students"); //จำนวนนักเรียนที่อยู่ในแต่ละบ้าน
                for (String student : member_inHouses.get(houseList[i])) { //list นักเรียนที่อยู่ในแต่ละบ้านออกมา
                    System.out.println("- " + student); //รายชื่อนักเรียนที่อยู่ในแต่ละบ้าน
                }
            }
        }

    }
     //รีเซ็ตชื่อนักเรียน จำนวนนักเรียนที่ถูกคัดสรร ในแต่ละบ้าน
    public void resetData() {
        System.out.println("[*] The information has been reset.");
        studentNames.clear(); //เคลียร์ชื่อ นักเรียนออก
        for (String house : houseList) { //เซ็ตให้เป็น map ว่างไว้
            counts_inHouses.put(house, 0);
            member_inHouses.put(house, new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter number of student:");

        try{  // เอาไว้ดักจับกรณีที่ ใส่จำนวนนักเรียน เป็นค่า Integer
            int N = Integer.parseInt(keyboard.nextLine());  //รับค่าจำนวนนักเรียน + เว้นบรรทัดใหม่
            Hogwarts_emergency The_sortingHat = new Hogwarts_emergency(N); //ส่งจำนวนนักเรียน
                      
            while (true) {
                if ((studentNames.size() + 1) > N) {  /*ถ้าจำนวนชื่อที่ใส่เข้ามาเกิน N*/
                    System.out.println("The number of students has been accepted.");
                    System.out.println(" reset - Enter to start a new round");
                    System.out.println(" exit  - Enter to out of Hogwart's go to Azkaban");
                    String check = keyboard.next();  /*ไว้รับค่า reset กับ exit*/
    
                    if ("reset".equals(check)) { 
                        The_sortingHat.resetData(); //รีเซ็ต  data นักเรียนที่ถูกคัดสรรก่อนหน้านี้ 
                        System.out.println("Enter number of student:");
                        int E = keyboard.nextInt(); //รับค่าจำนวนนักเรียนในรอบ reset
                        N = E; // ปรับให้ N เท่ากับ E จะให้เช็คค่าจำนวนชื่อที่ใส่เข้ามาเกิน N ในรอบ reset ได้
                        Hogwarts_emergency the_sortingHat2 = new Hogwarts_emergency(E);  // ส่งค่า E ไป แทน N
                        keyboard.nextLine(); // ขึ้นบรรทัดใหม่กัน
                        
                    } else if ("exit".equals(check)) {
                        System.exit(0); //จบการทำ work
                    }
    
                } else {  // จำนวนชื่อที่ใส่เข้ามาไม่เกิน N
                    System.out.print("[#] Enter student name: ");
                    String name = keyboard.nextLine(); //รับชื่อนักเรียน

                    if("".equals(name) || " ".equals(name) ){ //เช็คค่าว่างในการใส่ชื่อ
                        System.out.println("Please enter a valid name.");
                    }else{
                        TheSorting_Hat(name);  //ส่งชื่อนักเรียนไปให้หมวกคัดสรรทำนาย
                    }
                }
            }
        }catch(Exception e) {  //ถ้าจำนวนนักเรียนเป็นค่าที่ไม่ใช่ Integer
            System.out.println("Please enter the number of students in numbers");	            
            System.out.println("close the program and then restart it.");	          
       }

    }
}
