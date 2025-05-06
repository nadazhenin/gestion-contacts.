package com.gestioncontacts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
        loadContacts();
    }

    private void loadContacts() {
        Connection connexion = ConnexionBD.getConnexion();
        if (connexion != null) {
            try {
                Statement statement = connexion.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM contact");

                contacts.clear();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String telephone = resultSet.getString("telephone");
                    String mail = resultSet.getString("mail");

                    contacts.add(new Contact(id, nom, prenom, telephone, mail));
                }

                statement.close();
                connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addContact(Contact contact) {
        contactDAO.insererContact(contact.getNom(), contact.getPrenom(),
                contact.getTelephone(), contact.getEmail());
        loadContacts(); // Recharger les contacts depuis la base
    }

    public void updateContact(int index, Contact newContact) {
        if (index >= 0 && index < contacts.size()) {
            Contact oldContact = contacts.get(index);
            updateContactInDB(oldContact.getId(), newContact);
            loadContacts(); // Recharger les contacts depuis la base
        }
    }

    private void updateContactInDB(int id, Contact contact) {
        Connection connexion = ConnexionBD.getConnexion();
        if (connexion != null) {
            try {
                String sql = "UPDATE contact SET nom = ?, prenom = ?, telephone = ?, mail = ? WHERE id = ?";
                PreparedStatement statement = connexion.prepareStatement(sql);

                statement.setString(1, contact.getNom());
                statement.setString(2, contact.getPrenom());
                statement.setString(3, contact.getTelephone());
                statement.setString(4, contact.getEmail());
                statement.setInt(5, id);

                statement.executeUpdate();
                statement.close();
                connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            Contact contact = contacts.get(index);
            deleteContactFromDB(contact.getId());
            loadContacts(); // Recharger les contacts depuis la base
        }
    }

    private void deleteContactFromDB(int id) {
        Connection connexion = ConnexionBD.getConnexion();
        if (connexion != null) {
            try {
                String sql = "DELETE FROM contact WHERE id = ?";
                PreparedStatement statement = connexion.prepareStatement(sql);

                statement.setInt(1, id);
                statement.executeUpdate();

                statement.close();
                connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}