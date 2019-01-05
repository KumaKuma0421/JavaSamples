package Pattern.Command;

public abstract class Command {
    private Receiver receiver = null;
    
    @SuppressWarnings("unused")
    private Command() {
        
    }
    
    public Command(Receiver receiver) {
        this.setReceiver(receiver);
    }

    public final void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract void execute();
}
