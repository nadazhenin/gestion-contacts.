package com.gestioncontacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditContactFrame extends JFrame {
    private ContactManager contactManager;
    private JComboBox<Contact> contactComboBox;
    private JTextField nomField, prenomField, telephoneField, emailField;

    public EditContactFrame(ContactManager contactManager) {
        this.contactManager = contactManager;
        setTitle("Modifier un Contact");
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Sélectionner un contact:"));
        contactComboBox = new JComboBox<>();
        updateContactComboBox();
        panel.add(contactComboBox);

        panel.add(new JLabel("Nouveau Nom:"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Nouveau Prénom:"));
        prenomField = new JTextField();
        panel.add(prenomField);

        panel.add(new JLabel("Nouveau Téléphone:"));
        telephoneField = new JTextField();
        panel.add(telephoneField);

        panel.add(new JLabel("Nouvel Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        contactComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact selected = (Contact) contactComboBox.getSelectedItem();
                if (selected != null) {
                    nomField.setText(selected.getNom());
                    prenomField.setText(selected.getPrenom());
                    telephoneField.setText(selected.getTelephone());
                    emailField.setText(selected.getEmail());
                }
            }
        });

        JButton updateButton = new JButton("Mettre à jour");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String nom = nomField.getText();
                    String prenom = prenomField.getText();
                    String telephone = telephoneField.getText();
                    String email = emailField.getText();

                    if (!nom.isEmpty() && !prenom.isEmpty() && !telephone.isEmpty() && !email.isEmpty()) {
                        Contact newContact = new Contact(nom, prenom, telephone, email);
                        contactManager.updateContact(selectedIndex, newContact);
                        JOptionPane.showMessageDialog(EditContactFrame.this,
                                "Contact mis à jour avec succès",
                                "Succès",
                                JOptionPane.INFORMATION_MESSAGE);
                        updateContactComboBox();
                    } else {
                        JOptionPane.showMessageDialog(EditContactFrame.this,
                                "Veuillez remplir tous les champs",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateContactComboBox() {
        contactComboBox.removeAllItems();
        List<Contact> contacts = contactManager.getAllContacts();
        for (Contact contact : contacts) {
            contactComboBox.addItem(contact);
        }
    }
}
