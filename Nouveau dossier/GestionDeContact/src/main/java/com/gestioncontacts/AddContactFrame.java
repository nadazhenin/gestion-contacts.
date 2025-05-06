package com.gestioncontacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddContactFrame extends JFrame {
    private ContactManager contactManager;
    private JTextField nomField, prenomField, telephoneField, emailField;

    public AddContactFrame(ContactManager contactManager) {
        this.contactManager = contactManager;
        setTitle("Ajouter un Contact");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        panel.add(prenomField);

        panel.add(new JLabel("Téléphone:"));
        telephoneField = new JTextField();
        panel.add(telephoneField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String telephone = telephoneField.getText();
                String email = emailField.getText();

                if (!nom.isEmpty() && !prenom.isEmpty() && !telephone.isEmpty() && !email.isEmpty()) {
                    Contact contact = new Contact(nom, prenom, telephone, email);
                    contactManager.addContact(contact);
                    JOptionPane.showMessageDialog(AddContactFrame.this,
                            "Contact ajouté avec succès",
                            "Succès",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddContactFrame.this,
                            "Veuillez remplir tous les champs",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}