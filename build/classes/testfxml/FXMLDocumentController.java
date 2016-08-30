/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfxml;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author sameer
 *
 *
 *
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    public Label label, freq0, freq1, freq2, freq3, note;
    public TextField roundlow, roundmid, roundhigh;
    public Label volt0, volt1, volt2, volt3, temp0, temp1, temp2, temp3;
    public Button toggle, viewer, roundsubmit;
    boolean startlog = false, viewlog = false, roundrobin = false;
    public String passw = "sameer";
    public int set_success = 0;

    public FileWriter fw;
    public Object lock;

    public void setUser(String user_id) throws IOException {
        this.passw = user_id;

        if (this.passw.contentEquals(user_id)) {
            set_success = 1;
        }

        System.out.println(this.passw);
        initilizer();

        fw = null;
        lock = new Object();

        viewer.setDisable(true);
        roundsubmit.setVisible(false);
        roundlow.setVisible(false);
        roundmid.setVisible(false);
        roundhigh.setVisible(false);

    }
    String[] cur0, cur1, cur2, cur3, usr0, usr1, usr2, usr3, ond0, ond1, ond2, ond3, min0, min1, min2, min3, max0, max1, max2, max3, ged;
    String[] vol, vol0, vol1, vol2, vol3, temper0, temper1;

    public void close() throws Throwable {
        finalize();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        fw.close();
    }

    public void initilizer() {

        cur0 = new String[]{
            "/bin/sh",
            "-c",
            "echo " + this.passw + "  | sudo -S cat /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_cur_freq"

        };

        ged = new String[]{
            "gedit  sameer.sam1"
        };

        cur1 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + this.passw + "   | sudo -S cat /sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_cur_freq"
        };
        cur2 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + this.passw + "   | sudo -S cat /sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_cur_freq"
        };
        cur3 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + this.passw + "   | sudo -S cat /sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_cur_freq"
        };

        usr0 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S cpufreq-set -c0 -g userspace"
        };
        usr1 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S cpufreq-set -c1 -g userspace"
        };
        usr2 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S cpufreq-set -c2 -g userspace"
        };
        usr3 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S cpufreq-set -c3 -g userspace"
        };

        //settting freq to ondemnd
        ond0 = new String[]{
            "/bin/sh",
            "-c",
            "echo " + passw + " | sudo -S cpufreq-set -c0 -g ondemand"
        };
        ond1 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S cpufreq-set -c1 -g ondemand"
        };
        ond2 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S cpufreq-set -c2 -g ondemand"
        };
        ond3 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S cpufreq-set -c3 -g ondemand"
        };
        //setting cpu_freq to max == max
        max0 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S sudo cpufreq-set -c0 -f 3.2GHz"
        };
        max1 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S sudo cpufreq-set -c1 -f 3.2GHz"
        };
        max2 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S sudo cpufreq-set -c2 -f 3.2GHz"
        };
        max3 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S sudo cpufreq-set -c3 -f 3.2GHz"
        };

        //setting cpu_freq to min
        min0 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S sudo cpufreq-set -c0 -f 1.19GHz"
        };
        min1 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S sudo cpufreq-set -c1 -f 1.19GHz"
        };
        min2 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S sudo cpufreq-set -c2 -f 1.19GHz"
        };
        min3 = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S sudo cpufreq-set -c3 -f 1.19GHz"
        };

        //giving access to msr's
        vol = new String[]{
            "/bin/sh",
            "-c",
            "echo   " + passw + "   | sudo -S modprobe msr "
        };
        ///displaying voltage values..v11
        vol0 = new String[]{
            "/bin/sh",
            "-c",
            "echo    " + passw + "   | sudo -S rdmsr 408 --bitfield 47:32 --decimal --processor 0"
        };
//v22
        vol1 = new String[]{
            "/bin/sh",
            "-c",
            "echo    " + passw + "   | sudo -S rdmsr 408 --bitfield 47:32 --decimal --processor 1"
        };
//v33
        vol2 = new String[]{
            "/bin/sh",
            "-c",
            "echo    " + passw + "   | sudo -S rdmsr 408 --bitfield 47:32 --decimal --processor 2"
        };
//v44
        vol3 = new String[]{
            "/bin/sh",
            "-c",
            "echo    " + passw + "   | sudo -S rdmsr 408 --bitfield 47:32 --decimal --processor 3"
        };

        temper0 = new String[]{
            "/bin/sh",
            "-c",
            " sensors | grep \"Core 0\" "

        };

        temper1 = new String[]{
            "/bin/sh",
            "-c",
            " sensors | grep \"Core 1\" "

        };

    }

    public int ijk = 0;

    public void setpass(String pas) {
        this.passw = pas;
        System.out.println("Incoming pass " + this.passw);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void startlogger(ActionEvent event) throws Throwable {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        String gett = toggle.getText();
        switch (gett) {
            case "START LOG":
                if (fw == null) {
                    fw = new FileWriter("output.log");
                }
                toggle.setText("END LOG");
                startlog = true;
                viewlog = false;
                break;

            case "END LOG":
                viewlog = true;
                startlog = false;
                toggle.setText("START LOG");
                //close();
                break;

        }

        viewer.setDisable(!viewlog);

    }

    @FXML
    private void viewlogger(ActionEvent event) throws IOException, Throwable {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        close();
        fw = null;

        Runtime.getRuntime().exec(" gedit output.log");

    }

    @FXML
    public void ondemandButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        roundrobin = false;

        roundsubmit.setVisible(false);
        roundlow.setVisible(false);
        roundmid.setVisible(false);
        roundhigh.setVisible(false);

        synchronized (lock) {

            Process timer = Runtime.getRuntime().exec("date ");
            BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
            String timeo = "";
            String time = "";
            while ((timeo = seadertime.readLine()) != null) {
                time = timeo + " ";
            }

            if (startlog == true) {
                fw.write("---------------------------ONDEMAND mode started at time " + time + " ------------------------------- :\n ");
                fw.flush();
            }
        }

        try {
            // while (1==1) {
            //Thread.sleep(1000);
            //setting governor to ondemand
            Runtime.getRuntime().exec(ond0);
            Runtime.getRuntime().exec(ond1);
            Runtime.getRuntime().exec(ond2);
            Runtime.getRuntime().exec(ond3);

            //displaying frequency. f111
            //    }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @FXML
    public void highfreqButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");

        roundrobin = false;

        roundsubmit.setVisible(false);
        roundlow.setVisible(false);
        roundmid.setVisible(false);
        roundhigh.setVisible(false);

        synchronized (lock) {

            Process timer = Runtime.getRuntime().exec("date ");
            BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
            String timeo = "";
            String time = "";
            while ((timeo = seadertime.readLine()) != null) {
                time = timeo + " ";
            }

            if (startlog == true) {

                fw.write("------------------------HighFrequency mode started at " + time + " : ----------------------------------- \n ");
                fw.flush();
            }
        }

        try {
            //setting governor to userspace
            Runtime.getRuntime().exec(usr0);
            Runtime.getRuntime().exec(usr1);
            Runtime.getRuntime().exec(usr2);
            Runtime.getRuntime().exec(usr3);

            //setting frequency to minimum
            Runtime.getRuntime().exec(max0);
            Runtime.getRuntime().exec(max1);
            Runtime.getRuntime().exec(max2);
            Runtime.getRuntime().exec(max3);
        } catch (Exception ex) {
            System.out.println(ex);

        }

    }

    @FXML
    public void lowfreqButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");

        roundrobin = false;

        roundsubmit.setVisible(false);
        roundlow.setVisible(false);
        roundmid.setVisible(false);
        roundhigh.setVisible(false);

        synchronized (lock) {

            Process timer = Runtime.getRuntime().exec("date ");
            BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
            String timeo = "";
            String time = "";
            while ((timeo = seadertime.readLine()) != null) {
                time = timeo + " ";
            }

            if (startlog == true) {

                fw.write("-------------------------LowFrequency mode started at " + time + " -------------------- ---  :\n ");
                fw.flush();
            }
        }

        try {
            //setting governor to userspace
            Runtime.getRuntime().exec(usr0);
            Runtime.getRuntime().exec(usr1);
            Runtime.getRuntime().exec(usr2);
            Runtime.getRuntime().exec(usr3);

            //setting frequency to minimum
            Runtime.getRuntime().exec(min0);
            Runtime.getRuntime().exec(min1);
            Runtime.getRuntime().exec(min2);
            Runtime.getRuntime().exec(min3);
        } catch (Exception ex) {
            System.out.println(ex);

        }

    }

    @FXML
    public void roundsubmitbutton(ActionEvent event) throws IOException {

        roundrobin = true;

        String l, m, h;
        l = roundlow.getText();
        m = roundmid.getText();
        h = roundhigh.getText();

        synchronized (lock) {
            if (startlog == true) {

                Process timer = Runtime.getRuntime().exec("date ");
                BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                String timeo = "";
                String time = "";
                while ((timeo = seadertime.readLine()) != null) {
                    time = timeo + " ";
                }

                fw.write("-----------------------RoundFrequency mode started at  " + time + " -------------------------------  : \n");
                fw.flush();
            }

        }

        if (l.isEmpty() == true && m.isEmpty() == true && h.isEmpty() == true) {

            note.setText(note.getText() + "Enter all the fields");

        } else {
            try {
                //setting governor to userspace
                Runtime.getRuntime().exec(usr0);
                Runtime.getRuntime().exec(usr1);
                Runtime.getRuntime().exec(usr2);
                Runtime.getRuntime().exec(usr3);

                Thread t = new Thread() {

                    public void run() {
                        while (true && roundrobin == true) {

                            try {
                                //setting frequency of l
                                String[] set0 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c0 -f " + l + "GHz"};
                                String[] set1 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c1 -f " + l + "GHz"};
                                String[] set2 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c2 -f " + l + "GHz"};
                                String[] set3 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c3 -f " + l + "GHz"};

                                Runtime.getRuntime().exec(set0);
                                Runtime.getRuntime().exec(set1);
                                Runtime.getRuntime().exec(set2);
                                Runtime.getRuntime().exec(set3);

                                Thread.sleep(2000);

                                //setting frequency of m
                                String[] set00 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c0 -f " + m + "GHz"};
                                String[] set11 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c1 -f " + m + "GHz"};
                                String[] set22 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c2 -f " + m + "GHz"};
                                String[] set33 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c3 -f " + m + "GHz"};

                                Runtime.getRuntime().exec(set00);
                                Runtime.getRuntime().exec(set11);
                                Runtime.getRuntime().exec(set22);
                                Runtime.getRuntime().exec(set33);

                                Thread.sleep(2000);
                                //setting frequency of h
                                String[] set000 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c0 -f " + h + "GHz"};
                                String[] set111 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c1 -f " + h + "GHz"};
                                String[] set222 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c2 -f " + h + "GHz"};
                                String[] set333 = new String[]{"/bin/sh", "-c", "echo   " + passw + "   | sudo -S sudo cpufreq-set -c3 -f " + h + "GHz"};

                                Runtime.getRuntime().exec(set000);
                                Runtime.getRuntime().exec(set111);
                                Runtime.getRuntime().exec(set222);
                                Runtime.getRuntime().exec(set333);

                                Thread.sleep(2000);
                            } catch (Exception ex) {
                                System.out.println(ex);

                            }

                        }
                    }
                };

                t.start();

            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

    }

    @FXML
    public void roundfreqButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me round!");

        //roundrobin = true;
        roundsubmit.setVisible(true);
        roundlow.setVisible(true);
        roundmid.setVisible(true);
        roundhigh.setVisible(true);

        note.setText("Note : All available frequencies are 1.1 , 1.2, 1.8 ,2.2 , 2.5 Ghz");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Thread t1;

        t1 = new Thread() {
            public void run() {

                //     try {
                //    Runtime.getRuntime().exec("gedit sameer.sam");
                //     } catch (IOException ex) {
                //         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                //     }
                if (set_success == 1) {

                    try {
                        Runtime.getRuntime().exec(vol);
                    } catch (Exception ex) {
                        System.out.println("modprobe msr error" + vol[2]);
                        //  ex.printStackTrace();
                    }

                    System.out.println("modprobe msr error123  " + vol0[2]);
                }

                while (true) {

                    if (set_success == 1) {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Random rand = new Random();

                                    int temp = rand.nextInt();
                                    //if you change the UI, do it here !

                                    Process p1 = Runtime.getRuntime().exec(cur0);
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                                    String line = "";
                                    String liner = "";
                                    while ((line = reader.readLine()) != null) {
                                        //System.out.print(line + "\n");
                                        liner = line + " ";
                                    }
                                    freq0.setText(liner + " Khz");

                                    synchronized (lock) {
                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("frequency of core 0 : " + liner + " khz at " + time + "\n");
                                            fw.flush();
                                        }
                                    }

                                    Process p2 = Runtime.getRuntime().exec(cur1);

                                    System.out.println(cur1[2]);

                                    BufferedReader reader1 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
                                    String line1 = "";
                                    String liner1 = "";
                                    while ((line1 = reader1.readLine()) != null) {

                                        liner1 = line1 + " ";
                                    }
                                    freq1.setText(liner1 + " Khz");

                                    synchronized (lock) {
                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("frequency of  core 1 : " + liner + "khz at " + time + "\n");
                                            fw.flush();

                                        }
                                    }

                                    //f33
                                    Process p3 = Runtime.getRuntime().exec(cur2);
                                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(p3.getInputStream()));
                                    String line2 = "";
                                    String liner2 = "";
                                    while ((line2 = reader2.readLine()) != null) {

                                        liner2 = line2 + " ";
                                    }
                                    // System.out.println("afda "+liner2);
                                    freq2.setText(liner2 + " Khz");

                                    synchronized (lock) {
                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("frequency of core 2 : " + liner + " khz at " + time + " \n");
                                            fw.flush();
                                        }
                                    }

                                    //f44
                                    Process p4 = Runtime.getRuntime().exec(cur3);
                                    BufferedReader reader3 = new BufferedReader(new InputStreamReader(p4.getInputStream()));
                                    String line3 = "";
                                    String liner3 = "";
                                    while ((line3 = reader3.readLine()) != null) {

                                        liner3 = line3 + " ";
                                    }
                                    freq3.setText(liner3 + " Khz");

                                    synchronized (lock) {
                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("frequency of core 3 : " + liner + "khz at " + time + "\n");
                                            fw.flush();
                                        }
                                    }

                                    //displaying voltage. v11(label variable name)
                                    Process s1 = Runtime.getRuntime().exec(vol0);
                                    BufferedReader seader = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                                    String sine = "";
                                    String siner = "";
                                    while ((sine = seader.readLine()) != null) {
                                        siner = sine;
                                    }

                                    int foo = Integer.parseInt(siner);
                                    double fool = foo / Math.pow(2, 13);
                                    volt0.setText(fool + " V");

                                    synchronized (lock) {
                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("Voltage of core 0 : " + fool + "V at " + time + " \n");
                                            fw.flush();
                                        }
                                    }

                                    //v22 (label variable name)
                                    Process s2 = Runtime.getRuntime().exec(vol1);
                                    BufferedReader seader1 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
                                    String sine1 = "";
                                    String siner1 = "";
                                    while ((sine1 = seader1.readLine()) != null) {

                                        siner1 = sine1;
                                    }

                                    int foo1 = Integer.parseInt(siner1);
                                    double fool1 = foo1 / Math.pow(2, 13);
                                    volt1.setText(fool1 + " V");

                                    synchronized (lock) {

                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("Voltage of core 1 : " + fool1 + "V at " + time + "\n");
                                            fw.flush();
                                        }
                                    }

                                    //v33 label variable name
                                    Process s3 = Runtime.getRuntime().exec(vol2);
                                    BufferedReader seader2 = new BufferedReader(new InputStreamReader(s3.getInputStream()));
                                    String sine2 = "";
                                    String siner2 = "";
                                    while ((sine2 = seader2.readLine()) != null) {

                                        siner2 = sine2;
                                    }

                                    int foo2 = Integer.parseInt(siner2);
                                    double fool2 = foo2 / Math.pow(2, 13);
                                    volt2.setText(fool2 + " V");

                                    synchronized (lock) {

                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("Voltage of core 2 : " + fool2 + "V at " + time + "\n");
                                            fw.flush();
                                        }
                                    }

                                    //v44 label variable name
                                    Process s4 = Runtime.getRuntime().exec(vol3);
                                    BufferedReader seader3 = new BufferedReader(new InputStreamReader(s4.getInputStream()));
                                    String sine3 = "";
                                    String siner3 = "";
                                    while ((sine3 = seader3.readLine()) != null) {

                                        siner3 = sine3;
                                    }

                                    int foo3 = Integer.parseInt(siner3);
                                    double fool3 = foo3 / Math.pow(2, 13);
                                    volt3.setText(fool3 + " V");

                                    synchronized (lock) {
                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("Voltage of core 3 : " + fool3 + "V at " + time + "\n");
                                            fw.flush();
                                        }
                                    }

                                    //displaying temperature of cores
                                    Process tp = Runtime.getRuntime().exec(temper0);
                                    BufferedReader seadertp = new BufferedReader(new InputStreamReader(tp.getInputStream()));
                                    String sinetp = "";
                                    String sinertp = "";
                                    while ((sinetp = seadertp.readLine()) != null) {
                                        sinertp = sinetp + " ";
                                    }

                                    String[] parts = sinertp.split("\\s+");

                                    temp0.setText(parts[2]);
                                    temp1.setText(parts[2]);

                                    synchronized (lock) {
                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }

                                        if (startlog == true) {

                                            fw.write("Temp of core 0 : " + parts[2] + "V at " + time + "\n");
                                            fw.write("Temp of core 1 : " + parts[2] + "V at " + time + "\n");

                                            fw.flush();
                                        }
                                    }

                                    System.out.println("temp  " + temper0[2]);

                                    //displaying temperature of cores
                                    Process tp1 = Runtime.getRuntime().exec(temper1);
                                    BufferedReader seadertp1 = new BufferedReader(new InputStreamReader(tp1.getInputStream()));
                                    String sinetp1 = "";
                                    String sinertp1 = "";
                                    while ((sinetp1 = seadertp1.readLine()) != null) {
                                        sinertp1 = sinetp1 + " ";
                                    }

                                    String[] parts1 = sinertp1.split("\\s+");

                                    temp2.setText(parts1[2]);
                                    temp3.setText(parts1[2]);

                                    synchronized (lock) {
                                        Process timer = Runtime.getRuntime().exec("date ");
                                        BufferedReader seadertime = new BufferedReader(new InputStreamReader(timer.getInputStream()));
                                        String timeo = "";
                                        String time = "";
                                        while ((timeo = seadertime.readLine()) != null) {
                                            time = timeo + " ";
                                        }
                                        
                                        if (startlog == true) {

                                            fw.write("Temp of core 2 : " + parts1[2] + "V at " + time + "\n");
                                            fw.write("Temp of core 3 : " + parts1[2] + "V at " + time + " \n");

                                            fw.flush();
                                        }
                                    }

                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        });

                        try {
                            Thread.sleep(2000);
                        } catch (Exception ex) {
                        }

                    }
                }
            }
        };

        t1.start();

    }

}
