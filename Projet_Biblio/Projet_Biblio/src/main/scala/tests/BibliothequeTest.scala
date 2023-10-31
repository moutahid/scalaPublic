package tests
import classes._

object BibliothequeTest {
  def main(args: Array[String]): Unit = {
    // Crée une bibliothèque vide
    val maBibliotheque = new Bibliotheque()

    // Crée des livres
    val livre1 = new Livre("Livre 1", "Auteur 1", 2020)
    val livre2 = new Livre("Livre 2", "Auteur 2", 2015)
    val livre3 = new Livre("Livre 3", "Auteur 1", 2012)

    // Ajoute les livres à la bibliothèque
    maBibliotheque.ajouterLivre(livre1)
    maBibliotheque.ajouterLivre(livre2)
    maBibliotheque.ajouterLivre(livre3)

    // Affiche tous les livres dans la bibliothèque
    print("Tout les livres de la bibliothèque \n")
    maBibliotheque.afficherTousLesLivres()

    // Emprunte un livre
    maBibliotheque.emprunterLivre("Livre 1")

    // Rend le livre emprunté
    maBibliotheque.rendreLivre("Livre 1")

    // Cherche un livre par titre
    print("Recherche de livre par Titre Livre 2")
    maBibliotheque.chercherLivreParTitre("Livre 2")

    // Cherche un livre par auteur
    print("Recherche de livre par Auteur 1")
    maBibliotheque.chercherLivreParAuteur("Auteur 1")

    // Sauvegarde la bibliothèque dans un fichier CSV
    maBibliotheque.sauvegarderBibliothequeCSV()

    // Charge la bibliothèque depuis un fichier CSV
    maBibliotheque.chargerBibliothequeCSV()

    // Affiche tous les livres empruntés
    maBibliotheque.afficherTousLesLivresEmpruntes()

    // Affiche tous les livres disponibles
    maBibliotheque.afficherTousLesLivresDisponibles()
  }
}
