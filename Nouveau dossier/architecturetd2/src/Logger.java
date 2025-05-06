import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

class logger {

    private static logger instance ; ;
    private PrintWriter writer ;

    private logger () {

        try {
            FileWriter fileWriter =
                    new FileWriter("application.log", true);
            writer = new PrintWriter(fileWriter, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// methode por ecrire un message dans le fichier de journal
public static logger getInstance() {
if (instance == null){
instance = new logger ();
}
return instance ;
}
// Methode pur ecrire un message dans le fichier de journal
    public void log (String message){
    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        .format(new Date());
    writer.println(timestamp + ": " + message );
    }

    public void close() {
    }
}
