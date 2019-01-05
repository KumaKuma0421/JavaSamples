package Pattern.Command;

public class ConcreteCommand2 extends Command {
    protected Receiver receiver;

    public ConcreteCommand2(Receiver receiver) {
        super(receiver);
        this.setReceiver(receiver);
    }

    @Override
    public void execute() {
        this.receiver.load();
        this.receiver.record();
        this.receiver.stop();
        this.receiver.play();
        this.receiver.stop();
        this.receiver.record();
        this.receiver.stop();
        this.receiver.play();
        this.receiver.stop();
        this.receiver.eject();
    }
}
