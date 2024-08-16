package ejercicio;


public class Ejercicio {

    private static int availableTickets = 10;

    public static void main(String[] args) {

        Thread[] users = new Thread[20];

        for (int i = 0; i < users.length; i++) {
            users[i] = new Thread(new TicketBookingTask(), "User-" + i);
            users[i].start();
        }

        for (Thread user : users) {
            try {
                user.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Entradas disponibles al final: " + availableTickets);
    }

    static class TicketBookingTask implements Runnable {
        @Override
        public void run() {
            reserveTicket();
        }

        private void reserveTicket() {
            
            if (availableTickets > 0) {
                try {

                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if (availableTickets > 0) {
                    System.out.println(Thread.currentThread().getName() + " ha reservado una entrada.");
                    availableTickets--;
                } else {
                    System.out.println(Thread.currentThread().getName() + " no pudo reservar una entrada, no hay entradas disponibles.");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " no pudo reservar una entrada, no hay entradas disponibles.");
            }
        }
    }
}
