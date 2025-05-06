package architecture;

public class main {
    public static void main(String[] args) {
        Logger file =Logger.getInstance()  ;
        file.log("Hello World");
        file.close();
    }
}
