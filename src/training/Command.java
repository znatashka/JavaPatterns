package training;

public class Command {

    public static void main(String[] args) {
        Comp comp = new Comp();
        User user = new User(new StartCommand(comp), new StopCommand(comp), new ResetCommand(comp));
        user.startComputer();
        user.resetComputer();
        user.stopComputer();
    }
}

interface Command_ {
    void execute();
}

class Comp {
    void start() {
        System.out.println("Start");
    }

    void stop() {
        System.out.println("Stop");
    }

    void reset() {
        System.out.println("Reset");
    }
}

class StartCommand implements Command_ {
    private Comp computer;

    StartCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.start();
    }
}

class StopCommand implements Command_ {
    private Comp computer;

    StopCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.stop();
    }
}

class ResetCommand implements Command_ {
    private Comp computer;

    ResetCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.reset();
    }
}

class User {
    private Command_ start;
    private Command_ stop;
    private Command_ reset;

    User(Command_ start, Command_ stop, Command_ reset) {
        this.start = start;
        this.stop = stop;
        this.reset = reset;
    }

    void startComputer() {
        start.execute();
    }

    void stopComputer() {
        stop.execute();
    }

    void resetComputer() {
        reset.execute();
    }
}