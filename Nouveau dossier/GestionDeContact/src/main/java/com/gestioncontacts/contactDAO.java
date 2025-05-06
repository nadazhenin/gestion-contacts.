package com.gestioncontacts; // Déclaration du package

import java.sql.Connection; // Pour les connexions à la base
import java.sql.PreparedStatement; // Pour les requêtes préparées
import java.sql.SQLException; // Pour gérer les exceptions SQL

public class contactDAO { // Classe d'accès aux données pour les contacts
    public static void insererContact(String nom, String prenom, String telephone, String mail) {
        Connection connexion = ConnexionBD.getConnexion(); // Obtention d'une connexion
        if (connexion != null) { // Si la connexion est établie
            try {
                // Préparation de la requête SQL d'insertion
                String sql = "INSERT INTO contact (nom, prenom, telephone, mail) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connexion.prepareStatement(sql);

                // Paramétrage des valeurs dans la requête
                statement.setString(1, nom);
                statement.setString(2, prenom);
                statement.setString(3, telephone);
                statement.setString(4, mail);

                // Exécution de la requête
                int lignes = statement.executeUpdate();
                if (lignes > 0) { // Si des lignes ont été affectées
                    System.out.println("Contact ajouté avec succès !");
                }

                // Fermeture des ressources
                statement.close();
                connexion.close();
            } catch (SQLException e) { // Gestion des erreurs SQL
                System.out.println("Erreur lors de l'insertion !");
                e.printStackTrace();
            }
        } else { // Si la connexion a échoué
            System.out.println("Connexion à la base échouée.");
        }
    }
}