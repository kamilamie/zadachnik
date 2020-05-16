package ru.itis.zadachnik.parsing_services.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TryConnect {

    public static void main(String[] args) throws IOException {
        Document page = Jsoup.connect("https://acm.timus.ru/problem.aspx?space=1&num=1001").get();
        System.out.println(page);
        String text = "камила";
        int c = 1;
        for(int i = 0; i < text.length(); i++) {
            if(Character.UnicodeBlock.of(text.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                c++;
            }
        }
        if (c/text.length() > 0.5){
            System.out.println("rus");
        }

    }
}
