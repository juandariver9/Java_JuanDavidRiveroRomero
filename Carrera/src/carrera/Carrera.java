/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carrera;

class Atleta extends Thread {
    private String nombre;
    private int numeroAtleta;
    private static Object testigo = new Object();

    public Atleta(String nombre, int numeroAtleta) {
        this.nombre = nombre;
        this.numeroAtleta = numeroAtleta;
    }

    @Override
    public void run() {
        synchronized (testigo) {
            try {
                long startTime = System.currentTimeMillis();
                System.out.println(nombre + " inicio a correr");
                
                Thread.sleep((int) (Math.random() * 2000) + 9000); // esto es un random entre 9 y 11 segundos
                
                long endTime = System.currentTimeMillis();
                System.out.println(nombre + " terminó de correr en: " + (endTime - startTime) / 1000.0 + " segundos ");
                
                if (numeroAtleta < 4) {
                    System.out.println(nombre + " pasa el testigo.");
                    testigo.notify();
                }
            } catch (InterruptedException e) {
                
            }

            if (numeroAtleta < 4) {
                try {
                    testigo.wait();
                } catch (InterruptedException e) {
                    
                }
            }
        }
    }
}

class Carrera {
    public static void main(String[] args) {
        
        Atleta atleta1 = new Atleta("Un de UberEats", 1);
        Atleta atleta2 = new Atleta("El de Rappi", 2);
        Atleta atleta3 = new Atleta("El de DidiFood", 3);
        Atleta atleta4 = new Atleta("El de la tienda", 4);
        
        
        atleta1.start();
        atleta2.start();
        atleta3.start();
        atleta4.start();
    }
}
