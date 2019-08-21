package com.hackerrank.github.mis;
import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HTMLTOJava{
    public static void main(String args[]) {
        // Parse HTML String using JSoup library
        String HTMLSTring = "<!DOCTYPE html>" + "<html>" + "<head>" + "<title>JSoup Example</title>" + "</head>" + "<body>" + "<table><tr><td><h1>HelloWorld</h1></tr>" + "</table>" + "</body>" + "</html>";
        Document html = Jsoup.parse(HTMLSTring);
        String title = html.title();
        String h1 = html.body().getElementsByTag("h1").text();
        System.out.println("Input HTML String to JSoup :" + HTMLSTring);
        System.out.println("After parsing, Title : " + title); System.out.println("Afte parsing, Heading : " + h1);
        // JSoup Example 2 - Reading HTML page from URL
        Document doc;
        try {
            doc = Jsoup.connect("http://google.com/").get();
            title = doc.title();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Jsoup Can read HTML page from URL, title : " + title);
        // JSoup Example 3 - Parsing an HTML file in Java
        // Document htmlFile = Jsoup.parse("login.html", "ISO-8859-1"); //
        // wrong
        Document htmlFile = null;
        String fileName="src/main/resources/MphH.xhtml";
        File file=new File(fileName);
        try {
            htmlFile = Jsoup.parse(file, "ISO-8859-1");
        }
        catch (IOException e)
        {
        }
        String count = htmlFile.getElementById("size").text();

        System.out.println("count : " + count);
         }
        }