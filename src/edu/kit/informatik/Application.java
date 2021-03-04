package edu.kit.informatik;

import edu.kit.informatik.io.Session;

/**
 * Application class that provides entry point for the escape route calculator
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Application {
    /**
     * Entrypoint for Flow
     * @param args Arguments for application at start
     */
    public static void main(String[] args) {
        Session session1 = new Session();
        session1.run();
    }
}
