package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;
import org.springframework.stereotype.Service;

@Service
public class SocketClient {


    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private String prompt = "username:";
    private String prompt1 = "password:";
    private String logout = "LOGOUT;";
    private static SocketClient instance;

    public static SocketClient getInstance() {
        if (instance == null) {
            instance = new SocketClient();
        }
        return instance;
    }


    public void connect() {
        try {
            telnet.connect("10.248.140.124", 4100);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            readUntil(prompt);
            write("admin");
            readUntil(prompt1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SocketClient() {
        try {
            // Connect to the specified server
            telnet.connect("10.248.140.124", 4100);

            // Get input and output stream references
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

            // Log the user on
            readUntil(prompt);
            write("admin");
            readUntil(prompt1);
            write("admin");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void su(String password) {
        try {
            write("su");
            readUntil("Password: ");
            write(password);
            prompt = "#";
            readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String subscribe(String msisdn, boolean prov) {
        connect();
        String command = "SET:HLRSUB:MSISDN," + msisdn + ":PRBT,0;";
        if (prov)
            command = command.replace("PRBT,0", "PRBT,1");

        command = command.replace("\n", "");
        command = command.replace("\\n", "");
        write(command);
        String response = readUntil(prompt);
        logout();
        return response;
    }

    public void logout() {
        write(logout);
        try {
            telnet.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command) {
        try {
            write(command);
            System.out.println(command);
            return readUntil(prompt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            //SocketClient telnet = new SocketClient( "182.74.113.62", "root", "Think@321#$");
            //System.out.println("Got Connection..."); telnet.sendCommand("ps -ef ");
            //System.out.println("run command"); telnet.sendCommand("ls ");
            // System.out.println("run command 2"); telnet.disconnect();
            // System.out.println("DONE");
            SocketClient socketClient = SocketClient.getInstance();
            socketClient.connect();
            socketClient.sendCommand("add udmuser:imsi=001010617001007,msisdn=9876543207,ambr=def_ambr,nssai=def_nssai,arfb=def_arfb,sar=def_sar,rat=0,cn=3,smf_sel=def_snssai,sm_dat=1-000001&INTERNET&ims,eps_flag=1,eps_odb=64,hplmn_odb=24,ard=65,epstpl=def_eps,context_id=1,apn_context=010200000000");
            socketClient.logout();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

