package e.normal.simplemap;

/**
 * Created by normal on 2/16/18.
 */

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class XMLAct {
    // Declare variables
    TextView textview;
    static boolean xmlsuccess = false;
    public static NodeList nodelist;
    ProgressDialog pDialog;
    public static String txt = "";
    // Insert image URL
    String URL = "http://www.androidbegin.com/tutorial/XMLParseTutorial.xml";

    //@Override
    public String xmlactivityt() {

        new DownloadXML().execute(URL);

        return(txt);
    }

    // DownloadXML AsyncTask
    private static class DownloadXML extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... Url) {
            try {
                URL url = new URL(Url[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                // Download the XML file
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
                // Locate the Tag Name
                nodelist = doc.getElementsByTagName("item");

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void args) {
            try {
                for (int temp = 0; temp < nodelist.getLength(); temp++) {
                    Node nNode = nodelist.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        //

                        txt = getNode("date", eElement);
                        String count = getNode("count", eElement);
                        //= new Integer(
                        xmlsuccess = true;
                    }
                }
            }catch (Exception e){
                xmlsuccess = false;
            }
            // Close progressba
            // r
        }
    }

    // getNode function
    private static String getNode(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}