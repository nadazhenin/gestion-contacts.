package com.gestioncontacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private final ContactManager contactManager;

    public MainFrame() {
        this.contactManager = new ContactManager();
        initializeUI();
    }

    private void initializeUI() {
        // Configuration de la fenêtre principale
        setTitle("Gestionnaire de Contacts");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panel principal
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Bouton Ajouter
        JButton addButton = new JButton("Ajouter un contact");
        addButton.addActionListener(this::showAddContactFrame);
        panel.add(addButton);

        // Bouton Modifier
        JButton editButton = new JButton("Modifier un contact");
        editButton.addActionListener(this::showEditContactFrame);
        panel.add(editButton);

        // Bouton Supprimer
        JButton deleteButton = new JButton("Supprimer un contact");
        deleteButton.addActionListener(this::showDeleteContactFrame);
        panel.add(deleteButton);

        return panel;
    }

    private void showAddContactFrame(ActionEvent e) {
        AddContactFrame addFrame = new AddContactFrame(contactManager);
        addFrame.setVisible(true);
    }

    private void showEditContactFrame(ActionEvent e) {
        EditContactFrame editFrame = new EditContactFrame(contactManager);
        editFrame.setVisible(true);
    }

    private void showDeleteContactFrame(ActionEvent e) {
        DeleteContactFrame deleteFrame = new DeleteContactFrame(contactManager);
        deleteFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Initialiser la base de données
        DatabaseInitializer.initializeDatabase();

        // ✅ Tester la connexion à la base de données avant de lancer l'interface
        if (ConnexionBD.getConnexion() != null) {
            System.out.println("✅ Connexion à la base de données réussie !");
        } else {
            System.out.println("❌ Échec de connexion à la base de données.");
        }

        // 🖥 Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}