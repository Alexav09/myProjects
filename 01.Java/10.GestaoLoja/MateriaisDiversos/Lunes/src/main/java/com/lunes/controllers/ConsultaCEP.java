package com.lunes.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConsultaCEP {

    public static String buscarCep(String cep) {
        String json;

        try {
            URI uri = new URI("http://viacep.com.br/ws/" + cep + "/json");
            URL url = uri.toURL();
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder jsonSb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonSb.append(line.trim());
            }

            json = jsonSb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o CEP: " + e.getMessage(), e);
        }

        return json;
    }

    public static void main(String[] args) {
        String json = buscarCep("69046000");
        System.out.println(json);

        Map<String, String> mapa = new HashMap<>();

        Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
        while (matcher.find()) {
            String[] group = matcher.group().split(":");
            mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
        }

        System.out.println(mapa);
    }
}
