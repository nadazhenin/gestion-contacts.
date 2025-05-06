package com.gestioncontacts; // Déclaration du package

import java.sql.Connection; // Pour les connexions à la base de données
import java.sql.DriverManager; // Pour gérer le pilote JDBC
import java.sql.SQLException; // Pour gérer les exceptions SQL

public class ConnexionBD { // Classe de gestion de la connexion à la base de données
    // Constantes pour la configuration de la connexion
    private static final String URL = "jdbc:mysql://localhost:3306/db"; // URL de la base
    private static final String UTILISATEUR = "root"; // Nom d'utilisateur
    private static final String MOT_DE_PASSE = ""; // Mot de passe (vide ici)

    public static Connection getConnexion() { // Méthode pour obtenir une connexion
        Connection connexion = null; // Initialisation de la connexion
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du pilote JDBC
            connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE); // Établissement de la connexion
        } catch (ClassNotFoundException | SQLException e) { // Gestion des exceptions
            e.printStackTrace(); // Affichage de la stack trace en cas d'erreur
        }
        return connexion; // Retourne la connexion (ou null en cas d'échec)
    }
}