package training;

public class ChainOfResponsibilityApp {

    public static void main(String[] args) {
        SMSLogger smsLogger = new SMSLogger(Level.ERROR);
        FileLogger fileLogger = new FileLogger(Level.DEBUG);
        EmailLogger emailLogger = new EmailLogger(Level.INFO);

        fileLogger.setNext(emailLogger);
        smsLogger.setNext(fileLogger);

        smsLogger.writeMessage("all good", Level.INFO);
        smsLogger.writeMessage("debugging", Level.DEBUG);
        smsLogger.writeMessage("system is broken", Level.ERROR);
    }
}

enum Level {
    ERROR(1),
    DEBUG(2),
    INFO(3);

    private int priority;

    Level(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}

abstract class Logger {
    private Level priority;
    private Logger next;

    Logger(Level priority) {
        this.priority = priority;
    }

    void writeMessage(String message, Level level) {
        if (level.getPriority() <= priority.getPriority()) {
            write(message);
        }
        if (next != null) {
            next.writeMessage(message, level);
        }
    }

    abstract void write(String message);

    void setNext(Logger next) {
        this.next = next;
    }
}

class SMSLogger extends Logger {

    SMSLogger(Level priority) {
        super(priority);
    }

    @Override
    public void write(String message) {
        System.out.println(" SMS: `" + message + "`");
    }
}

class FileLogger extends Logger {

    FileLogger(Level priority) {
        super(priority);
    }

    @Override
    public void write(String message) {
        System.out.println(" write to file: `" + message + "`");
    }
}

class EmailLogger extends Logger {

    EmailLogger(Level priority) {
        super(priority);
    }

    @Override
    public void write(String message) {
        System.out.println(" e-main: `" + message + "`");
    }
}
