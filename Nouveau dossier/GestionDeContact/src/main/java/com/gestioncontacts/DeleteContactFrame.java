package com.gestioncontacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteContactFrame extends JFrame {
    private ContactManager contactManager;
    private JComboBox<Contact> contactComboBox;

    public DeleteContactFrame(ContactManager contactManager) {
        this.contactManager = contactManager;
        setTitle("Supprimer un Contact");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Sélectionner un contact à supprimer:"));
        contactComboBox = new JComboBox<>();
        updateContactComboBox();
        panel.add(contactComboBox);

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(
                            DeleteContactFrame.this,
                            "Êtes-vous sûr de vouloir supprimer ce contact?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        contactManager.deleteContact(selectedIndex);
                        JOptionPane.showMessageDialog(DeleteContactFrame.this,
                                "Contact supprimé avec succès",
                                "Succès",
                                JOptionPane.INFORMATION_MESSAGE);
                        updateContactComboBox();
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(deleteButton);

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