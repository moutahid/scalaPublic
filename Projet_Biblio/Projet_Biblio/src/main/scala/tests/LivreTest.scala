package tests
import classes.Livre

// Tests
object LivreTest {
  def main(args: Array[String]): Unit = {
    // Création d'une instance de Livre
    val monLivre = new Livre("Livre de Test", "Auteur de Test", 2020)

    // Test de la fonction emprunter()
    monLivre.emprunter() // Devrait afficher "Le livre "Livre de Test" a été emprunté."
    monLivre.emprunter() // Devrait afficher "Le livre "Livre de Test" est déjà emprunté."

    // Test de la fonction rendre()
    monLivre.rendre() // Devrait afficher "Le livre "Livre de Test" a été rendu."
    monLivre.rendre() // Devrait afficher "Le livre "Livre de Test" n'est pas emprunté."
  }
}