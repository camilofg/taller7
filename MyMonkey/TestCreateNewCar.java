import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestCreateNewCar {

    public static final String ADB_ROOT = "C:/Users/rcfor/AppData/Local/Android/sdk/platform-tools/";
    public static final String ADB_INPUT = ADB_ROOT + "adb shell input ";
    public static final String TELNET_TOKEN = "q0f0FJSGHgih2s/O";
    public static final String EMULATOR_PORT = "5554";


    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        try {
            String command = ADB_ROOT + "adb shell monkey -p me.kuehle.carreport -c android.intent.category.LAUNCHER 1";
            System.out.println(command);
            rt.exec(command);
            Thread.sleep(2000);
        

            rt.exec(ADB_INPUT + "tap " + 561 + " " + 1115);
            Thread.sleep(3000);

            //car name field
            rt.exec(ADB_INPUT + "text " + " 'Car Test' ");
            Thread.sleep(1000);
           
            //click mileage start field
            rt.exec(ADB_INPUT + "tap " + 95 + " " + 604);
            Thread.sleep(1000);


            //fill mileage start
            rt.exec(ADB_INPUT + "text " + " '1000' ");
            Thread.sleep(1000);


            //click button save
            rt.exec(ADB_INPUT + "tap " + 887 + " " + 131);//1114 + " " + 131);
            Thread.sleep(1000);


        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}



