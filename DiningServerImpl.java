
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl implements DiningServer {

    private final static ReentrantLock[] FORKS = new ReentrantLock[5];
    final static int NUM_OF_PHILS = 5;

    // Constructor which initializes an array of reentrant locks for the forks.
    public DiningServerImpl() {
        for (int i = 0; i < FORKS.length; i++) {
            FORKS[i] = new ReentrantLock();
        }
    }

    @Override
    /*
     * If the philosopher can pick up both forks, do so and return true.
     * Otherwise, return false and try again.
     */
    public boolean takeForks(int pNumber) {
        boolean forksTaken = false;
        switch (pNumber) {
            case 0:
                if (!FORKS[4].isLocked() && !FORKS[0].isLocked()) {
                    FORKS[4].lock();
                    FORKS[0].lock();
                    forksTaken = true;
                } 
                break;
            case 1:
                if (!FORKS[0].isLocked() && !FORKS[1].isLocked()) {
                    FORKS[0].lock();
                    FORKS[1].lock();
                    forksTaken = true;
                }
                break;
            case 2:
                if (!FORKS[1].isLocked() && !FORKS[2].isLocked()) {
                    FORKS[1].lock();
                    FORKS[2].lock();
                    forksTaken = true;
                }
                break;
            case 3:
                if (!FORKS[2].isLocked() && !FORKS[3].isLocked()) {
                    FORKS[2].lock();
                    FORKS[3].lock();
                    forksTaken = true;
                }
                break;
            case 4:
                if (!FORKS[3].isLocked() && !FORKS[4].isLocked()) {
                    FORKS[3].lock();
                    FORKS[4].lock();
                    forksTaken = true;
                }
                break;
        }
        return forksTaken;
    }

    @Override
    // Unlocks the forks held by the philosopher.
    public void returnForks(int pNumber) {
        switch (pNumber) {
            case 0:
                FORKS[4].unlock();
                FORKS[0].unlock();
                break;
            case 1:
                FORKS[0].unlock();
                FORKS[1].unlock();
                break;
            case 2:
                FORKS[1].unlock();
                FORKS[2].unlock();
                break;
            case 3:
                FORKS[2].unlock();
                FORKS[3].unlock();
                break;
            case 4:
                FORKS[3].unlock();
                FORKS[4].unlock();
                break;
        }
    }
}
