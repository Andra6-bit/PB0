
package praktikum5;
public class Hewan {
    String nama;
    String jenis;
    
    public void infoHewan(){
        System.out.println("Nama Hewan :" + nama);
        System.out.println("Jenis Hewan :" + jenis);
    
    } 
}

class Kucing extends Hewan {
    
    
    @Override
    public void infoHewan(){
        super.infoHewan();
        System.out.println("Suara khas Kucing ; Miaw Miawww " );
    }
}

class Anjing extends Hewan {
   
    
    @Override
    public void infoHewan(){
        super.infoHewan();
        System.out.println("Suara Khas Anjing : Woof Woof Woof " );
    }
}
