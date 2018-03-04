package e.normal.simplemap;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public  class SendEDI extends AsyncTask<String, Void, Void>
    {


        @Override
        protected Void doInBackground(String... params) {
            String outedi = "";

            outedi = MakeEDI.makeedi(MapsActivity.locmarkers);
            if (params[0] == "Retr") outedi = MakeEDI.makeedi("Retr");

            String inedi = "";
            Socket socket;
            Scanner scanner;
            try {
                //System.exit(0);
                socket = new Socket("192.168.4.1", 80);
                //System.exit(0);

                OutputStream raus = socket.getOutputStream();
                PrintStream ps = new PrintStream(raus, true);
                ps.println(outedi);

                //Thread.sleep(500);
                InputStream rein = socket.getInputStream();
                System.out.println("verf\u00FCgbare Bytes: " + rein.available());
                BufferedReader buff = new BufferedReader(new InputStreamReader(rein));

                while (buff.ready()) {
                    //System.exit(0);
                    System.out.println(buff.readLine());
                    inedi = buff.readLine();
                    //ReceivedEDI.receivededi(inedi);
                }
                if ((inedi != "")&&(inedi != outedi)){
                    RemoveMarkers.removemarkers(MapsActivity.locmarkers);;
                }
                socket.close();

            }catch(Exception e) {




            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {
                //Thread.sleep(5000);
                //new SendEDI().execute();


                super.cancel(true);
            }catch (Exception e){

            }

            //this method will be running on UI thread

        }

    }
