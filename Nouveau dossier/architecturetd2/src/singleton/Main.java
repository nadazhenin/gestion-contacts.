package singleton;

class Logger {
    private static Logger instance;

    // constructeur privé pour empêcher l'instanciation externe
    private Logger() {
        // initialisation si nécessaire
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
    }

    public void close() {
        System.out.println("Logger fermé.");
    }
}