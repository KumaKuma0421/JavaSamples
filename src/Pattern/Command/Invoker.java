package Pattern.Command;

import java.util.ArrayList;

public class Invoker {
    ArrayList<Command> commands;

    public Invoker() {
        this.commands = new ArrayList<Command>();
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void execute() {
        while (this.commands.size() > 0) {
            final var command = this.commands.get(0);
            command.execute();
            this.commands.remove(0);
        }
    }
}
