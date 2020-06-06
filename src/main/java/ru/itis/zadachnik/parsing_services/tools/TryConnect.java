package ru.itis.zadachnik.parsing_services.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TryConnect {

    public static void main(String[] args) throws IOException {
        Document page = Jsoup.connect("https://www.e-olymp.com/ru/problems/1").get();
        System.out.println(page);

    }
}
