package com;

import com.Contact;
import com.ContactService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//héritage de JFrame
public class ContactManagerUI extends JFrame {
    private ContactService contactService;
    private JTable contactsTable;
    private DefaultTableModel tableModel;

    public ContactManagerUI() {
        contactService = new ContactService();
        initializeUI();
        loadContacts();
    }

    private void initializeUI() {
        setTitle("Gestionnaire de Contacts");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du modèle de table
        String[] columnNames = {"Nom", "Prénom", "Téléphone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        contactsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contactsTable);

        // Boutons
        JButton addButton = new JButton("Ajouter");
        JButton deleteButton = new JButton("Supprimer");
        JButton searchButton = new JButton("Rechercher");

        // Champ de recherche
        JTextField searchField = new JTextField(20);

        // Panel pour les boutons et la recherche
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(new JLabel("Rechercher:"));
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);

        // Ajout des composants à la fenêtre
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Gestion des événements
        addButton.addActionListener(e -> showAddContactDialog());
        deleteButton.addActionListener(e -> deleteSelectedContact());
        searchButton.addActionListener(e -> searchContacts(searchField.getText()));
    }
// mettre les contactes dans une tablaux
    private void loadContacts() {
        tableModel.setRowCount(0); // Effacer les données existantes
        for (Contact contact : contactService.getAllContacts()) {
            addContactToTable(contact);
        }
    }

    private void addContactToTable(Contact contact) {
        Object[] rowData = {
                contact.getNom(),
                contact.getPrenom(),
                contact.getTelephone(),
                contact.getEmail()
        };
        tableModel.addRow(rowData);
    }

    private void showAddContactDialog() {
        JDialog addDialog = new JDialog(this, "Ajouter un contact", true);
        addDialog.setSize(400, 300);
        addDialog.setLayout(new GridLayout(5, 2));

        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField telephoneField = new JTextField();
        JTextField emailField = new JTextField();

        addDialog.add(new JLabel("Nom:"));
        addDialog.add(nomField);
        addDialog.add(new JLabel("Prénom:"));
        addDialog.add(prenomField);
        addDialog.add(new JLabel("Téléphone:"));
        addDialog.add(telephoneField);
        addDialog.add(new JLabel("Email:"));
        addDialog.add(emailField);

        JButton saveButton = new JButton("Enregistrer");
        JButton cancelButton = new JButton("Annuler");

        saveButton.addActionListener(e -> {
            Contact newContact = new Contact(
                    nomField.getText(),
                    prenomField.getText(),
                    telephoneField.getText(),
                    emailField.getText()
            );
            contactService.addContact(newContact);
            addContactToTable(newContact);
            addDialog.dispose();
        });

        cancelButton.addActionListener(e -> addDialog.dispose());

        addDialog.add(saveButton);
        addDialog.add(cancelButton);

        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }

    private void deleteSelectedContact() {
        int selectedRow = contactsTable.getSelectedRow();
        if (selectedRow >= 0) {
            Contact contact = new Contact(
                    (String) tableModel.getValueAt(selectedRow, 0),
                    (String) tableModel.getValueAt(selectedRow, 1),
                    (String) tableModel.getValueAt(selectedRow, 2),
                    (String) tableModel.getValueAt(selectedRow, 3)
            );
            contactService.deleteContact(contact);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un contact à supprimer", "Aucun contact sélectionné", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchContacts(String searchTerm) {
        tableModel.setRowCount(0); // Effacer les données existantes
        for (Contact contact : contactService.searchContacts(searchTerm)) {
            addContactToTable(contact);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContactManagerUI ui = new ContactManagerUI();
            ui.setVisible(true);
        });
    }
}