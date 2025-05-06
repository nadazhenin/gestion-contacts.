package architecture;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static Logger instance;
    private PrintWriter writer;
    private Logger() {
        try{
            FileWriter fileWriter =
                    new FileWriter( "application.log", true);
            writer = new PrintWriter(fileWriter , true);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }
    public void log(String message){
        String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        writer.println(timestamp + ": " + message);
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}
