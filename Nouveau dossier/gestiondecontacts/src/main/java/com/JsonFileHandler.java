package com;

import com.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
//creation de  fichier
public class JsonFileHandler {
    private static final String FILE_PATH = "contacts.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
//enregistrement de donnees
    public static void saveContacts(List<Contact> contacts) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(contacts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//chargement pour les contacte
    public static List<Contact> loadContacts() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type contactListType = new TypeToken<ArrayList<Contact>>(){}.getType();
            return gson.fromJson(reader, contactListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}