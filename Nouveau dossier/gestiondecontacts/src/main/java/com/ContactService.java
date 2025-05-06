package com;

import com.Contact;
import com.JsonFileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactService {
    private List<Contact> contacts;

    public ContactService() {
        contacts = JsonFileHandler.loadContacts();
    }
//ajouter
    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
    }
//supp
    public void deleteContact(Contact contact) {
        contacts.remove(contact);
        saveContacts();
    }
//recherche
    public List<Contact> searchContacts(String searchTerm) {
        return contacts.stream()
                .filter(contact -> contact.getNom().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        contact.getPrenom().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        contact.getTelephone().contains(searchTerm) ||
                        contact.getEmail().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    private void saveContacts() {
        JsonFileHandler.saveContacts(contacts);
    }
}