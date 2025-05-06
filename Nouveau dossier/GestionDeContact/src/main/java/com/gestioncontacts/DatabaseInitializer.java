

package com.gestioncontacts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        Connection connexion = ConnexionBD.getConnexion();
        if (connexion != null) {
            try {
                Statement statement = connexion.createStatement();

                // Requête de création de table
                String createTableSQL = "CREATE TABLE IF NOT EXISTS contact (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nom VARCHAR(50) NOT NULL, " +
                        "prenom VARCHAR(50) NOT NULL, " +
                        "telephone VARCHAR(20) NOT NULL, " +
                        "mail VARCHAR(100) NOT NULL)";

                statement.executeUpdate(createTableSQL);
                statement.close();
                connexion.close();

                System.out.println("✅ Table 'contact' créée ou déjà existante");
            } catch (SQLException e) {
                System.out.println("❌ Erreur lors de la création de la table");
                e.printStackTrace();
            }
        }
    }
}