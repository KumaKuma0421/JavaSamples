package Pattern.Command;

public class ConcreteCommand1 extends Command {
    protected Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        super(receiver);
        this.setReceiver(receiver);
    }

    @Override
    public void execute() {
        this.receiver.load();
        this.receiver.play();
        this.receiver.forward();
        this.receiver.play();
        this.receiver.forward();
        this.receiver.play();
        this.receiver.forward();
        this.receiver.play();
        this.receiver.stop();
        this.receiver.eject();
    }
}
