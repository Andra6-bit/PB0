
package PraktikumPBO_4Tugas;


public class Main {

    public static void main(String[] args) {
     Pekerja manusia1 = new Pekerja("QWERTY", 25, "Animator", 50000000);
     manusia1.tostring();
     manusia1.setNama("editQWERTY");
     manusia1.tostring();
     
     System.out.println(manusia1.getNama());
     System.out.println(manusia1.usia);
     System.out.println(manusia1.pekerjaan);
     System.out.println(manusia1.getGaji());
   
    }   
}
