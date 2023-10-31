import classes.Livre
import classes.Bibliotheque

object Main {
  def main(args: Array[String]): Unit = {
    val bibliotheque = new Bibliotheque()

    //charger la bibliothèque au début de l'application
    bibliotheque.chargerBibliothequeCSV()

    // Menu principal pour les cas d'utilisations
    while (true) {
      println("Que souhaitez-vous faire ?")
      println("1. Ajouter un livre")
      println("2. Emprunter un livre")
      println("3. Rendre un livre")
      println("4. Chercher un livre par titre")
      println("5. Chercher un livre par auteur")
      println("6. Afficher tous les livres")
      println("7. Supprimer un livre si disponible")
      println("8. Quitter")

      println("Veuillez entrer le numéro de votre choix : ")
      // lecture de l'entrée de l'utilisateur
      val choix = scala.io.StdIn.readInt()

      // traitement de chaque entrée de l'utilisateur
      choix match {
        case 1 =>
          val titre = scala.io.StdIn.readLine("Titre du livre : ")
          val auteur = scala.io.StdIn.readLine("Auteur : ")
          print("Année : ")
          val annee = scala.io.StdIn.readInt()
          val livre = new Livre(titre, auteur, annee)
          bibliotheque.ajouterLivre(livre)
          // sauvegarde de la bibliothèque après ajout
          bibliotheque.sauvegarderBibliothequeCSV()
        case 2 =>
          bibliotheque.afficherTousLesLivresDisponibles()
          val titre = scala.io.StdIn.readLine("Titre du livre à emprunter : ")
          bibliotheque.emprunterLivre(titre)
          // sauvegarde de la bibliothèque après emprunt
          bibliotheque.sauvegarderBibliothequeCSV()
        case 3 =>
          bibliotheque.afficherTousLesLivresEmpruntes()
          val titre = scala.io.StdIn.readLine("Titre du livre à rendre : ")
          bibliotheque.rendreLivre(titre)
          // sauvegarde de la bibliothèque après rendre
          bibliotheque.sauvegarderBibliothequeCSV()
        case 4 =>
          val titre = scala.io.StdIn.readLine("Titre du livre à chercher : ")
          bibliotheque.chercherLivreParTitre(titre)
        case 5 =>
          val auteur = scala.io.StdIn.readLine("Auteur du livre à chercher : ")
          bibliotheque.chercherLivreParAuteur(auteur)
        case 6 =>
          bibliotheque.afficherTousLesLivres()
        case 7 =>
          bibliotheque.afficherTousLesLivresDisponibles()
          val titre = scala.io.StdIn.readLine("Titre du livre à supprimer (si disponible) : ")
          bibliotheque.supprimerLivre(titre)
          // sauvegarde de la bibliothèque après suppression
          bibliotheque.sauvegarderBibliothequeCSV()
        case 8 => return // fin de l'applicationa
        case _ => println("Choix invalide. Veuillez sélectionner une option valide.")
      }
    }
  }
}
