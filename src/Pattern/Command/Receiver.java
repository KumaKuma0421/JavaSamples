package Pattern.Command;

public interface Receiver {
    public abstract void play();

    public abstract void stop();

    public abstract void forward();

    public abstract void reverse();

    public abstract void record();

    public abstract void eject();

    public abstract void load();
}
