package training;

import java.util.ArrayList;
import java.util.List;

public class MediatorApp {

    public static void main(String[] args) {
        TextChat chat = new TextChat();

        UserM admin = new Admin(chat, "Админ");
        UserM u1 = new SimpleUser(chat, "П1");
        UserM u2 = new SimpleUser(chat, "П2");
        UserM u3 = new SimpleUser(chat, "П3");

        u2.setEnabled(false);

        chat.setAdmin(admin);
        chat.addUser(u1);
        chat.addUser(u2);
        chat.addUser(u3);

        u1.sendMessage("Привет");
        System.out.println("###");
        admin.sendMessage("Привет");
    }
}

abstract class UserM {

    private Chat chat;
    private String name;
    private boolean enabled = true;

    UserM(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    abstract void getMessage(String message);

    String getName() {
        return name;
    }

    boolean isEnabled() {
        return enabled;
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserM{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Admin extends UserM {

    Admin(Chat chat, String name) {
        super(chat, name);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Администратор `" + getName() + "` получает сообщение `" + message + "`");
    }
}

class SimpleUser extends UserM {

    SimpleUser(Chat chat, String name) {
        super(chat, name);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Пользователь `" + getName() + "` получает сообщение `" + message + "`");
    }
}

interface Chat {
    void sendMessage(String message, UserM user);
}

class TextChat implements Chat {

    private UserM admin;
    private List<UserM> users = new ArrayList<>();

    void setAdmin(UserM admin) {
        if (admin != null && admin instanceof Admin) {
            this.admin = admin;
        } else {
            throw new RuntimeException("Не хватает прав");
        }
    }

    void addUser(UserM user) {
        if (admin == null) {
            throw new RuntimeException("В чате нет админа");
        }

        if (user instanceof SimpleUser) {
            users.add(user);
        } else {
            throw new RuntimeException("Админ не может входить в другой чат");
        }
    }

    @Override
    public void sendMessage(String message, UserM user) {
        if (user instanceof Admin) {
            for (UserM u : users) {
                u.getMessage(user.getName() + ": " + message);
            }
        }

        if (user instanceof SimpleUser) {
            for (UserM u : users) {
                if (u != user && u.isEnabled()) {
                    u.getMessage(user.getName() + ": " + message);
                }
            }
            if (admin.isEnabled()) {
                admin.getMessage(user.getName() + ": " + message);
            }
        }
    }
}