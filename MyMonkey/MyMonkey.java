import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Random;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;


public class MyMonkey {

    public static final String ADB_ROOT = "C:/Users/rcfor/AppData/Local/Android/sdk/platform-tools/";
    public static final String ADB_INPUT = "adb shell input ";
    public static final String TELNET_TOKEN = "q0f0FJSGHgih2s/O";
    public static final String EMULATOR_PORT = "5554";
    public static Hashtable<String, String> arguments = new Hashtable<String, String>();

    public static ArrayList<String> eventsToRun = new ArrayList<String>();

    public static ArrayList<Integer> probabilitiesEvents = new ArrayList<Integer>();

    public static int finalRange = 10;

    //public static List<Events> eventsToRun2 = new List<Events>();

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
        System.out.println("Rotate device");
    }

    public static void installAPK(String file) throws IOException {
        if(file == null) return;
        Runtime rt = Runtime.getRuntime();
        String command = ADB_ROOT + "adb install -r " + file;
        System.out.println(command);
        try{
            rt.exec(command);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } 

    public static void openAPK(String packageName) throws IOException {
        if(packageName == null) return;
        try {
            Thread.sleep(10000);
            Runtime rt = Runtime.getRuntime();
            String command = ADB_ROOT + "adb shell monkey -p " + packageName + " -c android.intent.category.LAUNCHER 1";
            System.out.println(command);
            rt.exec(command);
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void mapArgs(String[] args){
        for(String str : args){
            System.out.println(str);
            String[] parts = str.split("=");
            arguments.put(parts[0], parts[1]);
        }
        System.out.println(arguments);
    }

    public static void eventsList(String events){
        if(events != null)
            eventsToRun = new ArrayList<String>(Arrays.asList(events.split(",")));
        else{
            eventsToRun.add("tap");
            eventsToRun.add("text");
            eventsToRun.add("swipe");
            eventsToRun.add("rotate");
            eventsToRun.add("keyevent");
            eventsToRun.add("network speed");
            eventsToRun.add("sensor set");
        }
        System.out.println(eventsToRun);
    }

    public static Boolean probabilitiesList(String probabilities, Integer sizeEvents){
        
        if(probabilities != null){
            String[] strArray = probabilities.split(",");
            Integer totalProbabilites = 0;
            for(int i = 0; i < strArray.length; i++) {
                probabilitiesEvents.add((Integer.parseInt(strArray[i])*finalRange)/10);
                totalProbabilites += Integer.parseInt(strArray[i]);
            }
            System.out.println(probabilitiesEvents);
            if(totalProbabilites <= 10)
                return true;
            else 
                return false;
        }
        else{
            Integer val = (int)Math.ceil((double)finalRange / sizeEvents);
            for(int i = 0; i < sizeEvents; i++) {
                probabilitiesEvents.add(val);
            }
            System.out.println(probabilitiesEvents);
            return true;
        }
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        mapArgs(args);
        try {
            Random random = new Random(12345);
            int i = 0;
            installAPK(arguments.get("installer"));
            openAPK(arguments.get("package"));
            
            if(arguments.get("events") != null){
                try {  
                    finalRange = Integer.parseInt(arguments.get("events"));  
                } 
                catch (NumberFormatException e) {  
                    throw e;
                }  
            }
            eventsList(arguments.get("selectedEvents"));
            probabilitiesList(arguments.get("distribution"), eventsToRun.size());
            while(i < finalRange) {
                int index = new Random().nextInt(eventsToRun.size());
                String funcToRun = eventsToRun.get(index);
                int numEvents = probabilitiesEvents.get(index);
                if(numEvents == 0) {
                    System.out.println("evento en el cual termina ejecucion: " + funcToRun); 
                }
                else {
                    System.out.println(funcToRun);
                    switch(funcToRun){
                        case "tap":
                            int x = random.nextInt(1080);
                            int y = random.nextInt(1920);

                            rt.exec(ADB_INPUT + "tap " + x + " " + y);
                            i++;
                            probabilitiesEvents.set(index, numEvents - 1);
                            Thread.sleep(1000);
                        break;
                        case "text":
                            rt.exec(ADB_INPUT + "text " + " 'Paris' ");
                            i++;
                            probabilitiesEvents.set(index, numEvents - 1);
                            Thread.sleep(1000);
                        break;
                        case "swipe":
                            int x0 = random.nextInt(1080);
                            int y0 = random.nextInt(1920);
                            
                            int x1 = random.nextInt(1080);
                            int y1 = random.nextInt(1920);
                            
                            rt.exec(ADB_INPUT + "swipe " + x0 + " " + y0 + " " + x1 + " " + y1);
                            i++;
                            probabilitiesEvents.set(index, numEvents - 1);
                            Thread.sleep(1000);
                        break;
                        case "rotate":
                            rotate();
                            i++;
                            probabilitiesEvents.set(index, numEvents - 1);
                            Thread.sleep(1000);
                        break;
                        case "keyevent":
                            int event = new Random().nextInt(1024);
                            rt.exec(ADB_INPUT + "keyevent "+ event);
                            System.out.println("Random keyEvent with " + event);
                            i++;
                            probabilitiesEvents.set(index, numEvents - 1);
                            Thread.sleep(1000);
                        break;
                        case "network speed":
                            changeInternetVelocity();
                            i++;
                            probabilitiesEvents.set(index, numEvents - 1);
                            Thread.sleep(1000);
                        break;
                        case "sensor set":
                            changeAccelerometerRead();
                            i++;
                            probabilitiesEvents.set(index, numEvents - 1);
                            Thread.sleep(1000);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeAccelerometerRead() throws IOException {
        BufferedWriter out = connectToTelnet();
        out.write("auth "+TELNET_TOKEN+"\n");
        out.write("sensor set acceleration 2.23517e-07:9.77631:0.812348\n");
        out.write("quit\n");
        out.flush();
        System.out.println("Change accelerometer read to: 2.23517e-07:9.77631:0.812348");
    }

    public static void changeInternetVelocity() throws IOException {
        int minVel = new Random().nextInt(75000);
        int maxVel = new Random().nextInt(280000);
        
        BufferedWriter out = connectToTelnet();
        out.write("auth "+TELNET_TOKEN+"\n");
        out.write("network speed " + minVel + " " + maxVel + "\n");
        out.write("quit\n");
        out.flush();
        System.out.println("Change internet speed with min: " + minVel + "and max: " + maxVel);
    }
}
