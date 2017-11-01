import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static final String ADB_ROOT = "C:/Users/rcfor/AppData/Local/Android/sdk/platform-tools/";
    public static final String ADB_INPUT = ADB_ROOT + "adb shell input ";
    public static final String TELNET_TOKEN = "q0f0FJSGHgih2s/O";
    public static final String EMULATOR_PORT = "5554";

    private static BufferedWriter connectToTelnet() throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process telnet = rt.exec("telnet localhost "+EMULATOR_PORT);
        return new BufferedWriter(new OutputStreamWriter(telnet.getOutputStream()));
    }

    public static void rotate() throws IOException {
        BufferedWriter out = connectToTelnet();
        out.write("auth "+TELNET_TOKEN+"\n");
        out.write("rotate\n");
        out.write("quit\n");
        out.flush();
    }

    public static void main(String[] args) {
        
        
        try{
            //rt.exec("adb -e shell monkey --ignore-crashes -p me.kuehle.carreport -c android.intent.category.LAUNCHER 1"); 
            if(args.length>0){
                Process p1 = Runtime.getRuntime().exec("adb shell install me.kuehle.carreport");

                //Process p = Runtime.getRuntime().exec(ADB_ROOT + "adb install " + args[0]);
                //Process rt = Runtime.getRuntime().exec("adb shell monkey -p " + args[0] + " -c android.intent.category.LAUNCHER 1");
                //rt.exec(ADB_ROOT + "adb install " + args[0]);//example.apk
                // rt.exec("adb shell monkey -p " + args[0] + " -c android.intent.category.LAUNCHER 1");
                // rt.exec("adb -e shell monkey --ignore-crashes -p me.kuehle.carreport -c android.intent.category.LAUNCHER 1"); 
                //rt.waitFor();
                // String line;
                // BufferedReader input = new BufferedReader(new InputStreamReader(rt.getInputStream()));
                // while ((line = input.readLine()) != null) {
                //     System.out.println(line);
                // }
                // input.close();
            }
            Main.rotate();
            Main.rotate();
        }
        //Main.rotate();

        // Runtime rt = Runtime.getRuntime();
        // try {

        //     Random random = new Random(12345);

        //     int i = 0;
        //     while(i < 10) {
        //         int x = random.nextInt(1080);
        //         int y = random.nextInt(1920);
        //         int rotate = random.nextInt(2);

        //         if (rotate == 1) {
        //             Main.rotate();
        //         }

        //         rt.exec(ADB_INPUT + "tap " + x + " " + y);
        //         i++;
        //         Thread.sleep(1000);
        //     }
        //}
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}