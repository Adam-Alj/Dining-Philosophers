
import java.util.Random;

public class Philosopher implements Runnable {

    final int philNum;
    final DiningServer server;
    final String philName;
    final Random random = new Random();

    Philosopher(DiningServer serv, int num) {
        philNum = num;
        server = serv;
        switch (philNum) {
            case 0:
                philName = "Descartes";
                break;
            case 1:
                philName = "Kant";
                break;
            case 2:
                philName = "Hume";
                break;
            case 3:
                philName = "Socrates";
                break;
            case 4:
                philName = "Plato";
                break;
            default:
                philName = "Unknown";
                break;
        }
    }

    @Override
    /*
     * If takeforks returns true, then the philosopher has acquired the
     * locks on his/her respective forks. In this case generate random eat
     * and think times and output state information.
     */
    public void run() {
        int eatTime;
        int thinkTime;
        while (true) {
            if (server.takeForks(philNum)) {

                eatTime = random.nextInt(5000) + 2000;
                thinkTime = random.nextInt(5000) + 3001;
                System.out.println(philName +"( " + philNum + " ) is eating...");

                // Starts eating...
                try {
                    Thread.sleep(eatTime);
                } catch (InterruptedException ex) {
                    System.exit(0);
                }
               
                // Stops eating...
                server.returnForks(philNum);
                System.out.println(philName +"( " + philNum 
                        + " ) is done eating and is now "
                        + "thinking...");
                
                // Starts to think,
                try {
                    Thread.sleep(thinkTime);
                } catch (InterruptedException ex) {
                    System.exit(0);
                }
                
                // Stops thinking, lets us know he/she is hungry!
                System.out.println(philName +"( " + philNum + " ) is hungry!");
            }
        }
    }
}
