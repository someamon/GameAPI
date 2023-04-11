package de.amondev.strider.util;

import java.util.Random;

public class SessionId {

    public static String generatorChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static SessionId generate() {
        return generate(8);
    }

    public static SessionId generate(int chars) {
        StringBuilder token = new StringBuilder();

        for(int i = 0; i < chars; i++) {
            token.append(generatorChars.charAt(new Random().nextInt(generatorChars.length())));
        }

        return new SessionId(token.toString());
    }

    private final String id;

    public SessionId(String id) {
        this.id = id;
    }

    public String string() {
        return id;
    }
    public String toString() {
        return string();
    }
}
