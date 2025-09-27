
package praktikum6;

/**
 *
 * @author Pongo
 */
public class Hewan {
    public void bersuara () {
        System.out.println("Hewan bersuara");
    }
    public void makan (String makanan) {
        System.out.println("Hewan makan " + makanan);
    }
    public void makan (String makanan, int jumlah) {
        System.out.println("Hewan makan " + jumlah + "porsi" + makanan);
    }
}

class Kucing extends Hewan {
    @Override
    public void bersuara () {
        System.out.println("Meow");
    }
} 

class Anjing extends Hewan {
    @Override
    public void bersuara() {
        System.out.println("woof");
    }
}
