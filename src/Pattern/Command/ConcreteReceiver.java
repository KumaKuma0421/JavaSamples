package Pattern.Command;

public class ConcreteReceiver implements Receiver {
    private boolean isSetCasette;

    public ConcreteReceiver() {
        this.isSetCasette = false;
    }

    public boolean canPlay() {
        return this.isSetCasette;
    }

    @Override
    public void play() {
        if (this.canPlay()) {
            System.out.println("Now play the music.");
        }
    }

    @Override
    public void stop() {
        if (this.canPlay()) {
            System.out.println("Now stop the music.");
        }
    }

    @Override
    public void forward() {
        if (this.canPlay()) {
            System.out.println("Now forwarding.");
        }
    }

    @Override
    public void reverse() {
        if (this.canPlay()) {
            System.out.println("Now reversing.");
        }
    }

    @Override
    public void record() {
        if (this.canPlay()) {
            System.out.println("Now recording.");
        }
    }

    @Override
    public void eject() {
        System.out.println("Now ejecting the casette.");
        this.isSetCasette = false;
    }

    @Override
    public void load() {
        System.out.println("Now loading the casette.");
        this.isSetCasette = true;
    }
}
