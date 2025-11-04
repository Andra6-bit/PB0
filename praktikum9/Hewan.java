/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum9;

/**
 *
 * @author Pongo
 */
abstract class Hewan {
    abstract void bersuara();
    void info() {
        System.out.println("Ini adalah hewan");
    }
}

class Anjing extends Hewan {
    @Override
    void bersuara() {
        System.out.println("Anjing mengeluarkan suara: Guk Guk");
    }
}

class Kucing extends Hewan {
    @Override
    void bersuara() {
        System.out.println("Kucing itu bersuara: Meow Meow");
    }
}
