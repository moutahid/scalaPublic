package classes
import java.io.{BufferedWriter, FileWriter}
import scala.collection.mutable.ListBuffer
import scala.io.Source.fromResource

// Définition de la classe Bibliotheque
class Bibliotheque(var livres: List[Livre] = List()) {

  // Fonction pour ajouter un livre à la bibliothèque
  def ajouterLivre(livre: Livre): Unit = {
    if (livres.exists(_.titre == livre.titre)) {
      println(s"Un livre avec le titre \"${livre.titre}\" existe déjà dans la bibliothèque. Le livre n'a pas été ajouté.")
    } else {
      livres = livre :: livres
      println(s"Le livre \"${livre.titre}\" a été ajouté à la bibliothèque.")
    }
  }

  def supprimerLivre(titre: String): Unit = {
    // Recherche l'indice du livre dans la liste mais aussi qu'il ne soit pas empruntés
    val livreIndex = livres.indexWhere(livre => livre.titre == titre && !livre.estEmprunte)
    if (livreIndex != -1) { // Vérifie si le livre a été trouvé
      livres = livres.patch(livreIndex, Nil, 1) // Supprime le livre de la liste
      println(s"Le livre \"$titre\" a été supprimé de la bibliothèque.")
    } else {
      println(s"Le livre \"$titre\" n'a pas été trouvé dans la bibliothèque.")
    }
  }

  // Fonction pour emprunter un livre par titre
  def emprunterLivre(titre: String): Unit = {
    val livre = livres.find(_.titre == titre)
    livre match {
      case Some(l) =>
        if (l.estEmprunte) {
          println(s"Le livre \"$titre\" est déjà emprunté.")
        } else {
          l.emprunter()
        }
      case None =>
        println(s"Le livre \"$titre\" n'est pas dans la bibliothèque.")
    }
    afficherTousLesLivresEmpruntes()
  }

  // Fonction pour rendre un livre par titre
  def rendreLivre(titre: String): Unit = {
    val livre = livres.find(_.titre == titre)
    livre match {
      case Some(l) =>
        if (!l.estEmprunte) {
          println(s"Le livre \"$titre\" est déjà disponible.")
          afficherTousLesLivresDisponibles()
        } else {
          l.rendre()
          afficherTousLesLivresDisponibles()
        }
      case None =>
        println(s"Le livre \"$titre\" n'est pas dans la bibliothèque.")
    }
  }

  // Fonction pour chercher un livre par titre
  def chercherLivreParTitre(titre: String): Unit = {
    val livre = livres.find(_.titre == titre)
    livre match {
      case Some(l) =>
        println(s"Livre trouvé :")
        println(s"Titre : ${l.titre}")
        println(s"Auteur : ${l.auteur}")
      case None =>
        println(s"Aucun livre trouvé avec le titre : \"$titre\".")
    }
  }

  // Fonction pour chercher un livre par auteur
  def chercherLivreParAuteur(auteur: String): Unit = {
    val livre = livres.find(_.auteur == auteur)
    livre match {
      case Some(l) =>
        println(s"Livre trouvé :")
        println(s"Titre : ${l.titre}")
        println(s"Auteur : ${l.auteur}")
      case None =>
        println(s"Aucun livre trouvé pour l'auteur \"$auteur\".")
    }
  }

  // Fonction pour afficher tous les livres de la bibliothèque
  def afficherTousLesLivres(): Unit = {
    if (livres.isEmpty) {
      println("La bibliothèque est vide.")
    } else {
      println("Livres dans la bibliothèque :")
      for (livre <- livres) {
        println(s"Titre : ${livre.titre}")
        println(s"Auteur : ${livre.auteur}")
        println(s"Année de publication : ${livre.anneeDePublication}")
        if (livre.estEmprunte) {
          println("Statut : Emprunté")
        } else {
          println("Statut : Disponible")
        }
        println() // Laissez un espace entre chaque livre
      }
    }
  }

  // Fonction pour afficher tous les livres empruntés
  def afficherTousLesLivresEmpruntes(): Unit = {
    val livresEmpruntes = livres.filter(_.estEmprunte)
    // Test sur la liste des livres empruntés -> vide ou non
    if (livresEmpruntes.isEmpty) {
      println("Aucun livre emprunté dans la bibliothèque.")
    } else {
      println("Livres empruntés dans la bibliothèque :")
      for (livre <- livresEmpruntes) {
        println(s"Titre : ${livre.titre}")
        println(s"Auteur : ${livre.auteur}")
        println(s"Année de publication : ${livre.anneeDePublication}")
        println()
      }
    }
  }

  // Fonction pour afficher tous les livres disponibles
  def afficherTousLesLivresDisponibles(): Unit = {
    val livresDisponibles = livres.filterNot(_.estEmprunte)
    // Test sur la liste des livres disponibles -> vide ou non
    if (livresDisponibles.isEmpty) {
      println("Aucun livre disponible dans la bibliothèque.")
    } else {
      println("Livres disponibles dans la bibliothèque :")
      for (livre <- livresDisponibles) {
        println(s"Titre : ${livre.titre}")
        println(s"Auteur : ${livre.auteur}")
        println(s"Année de publication : ${livre.anneeDePublication}")
        println()
      }
    }
  }

  // Fonction pour sauvegarder la bibliothèque dans un fichier CSV
  def sauvegarderBibliothequeCSV(): Unit = {
    val cheminFichier = "src\\main\\ressources\\biblio.csv"
    // format d'écriture dans le fichier biblio.csv -> propriétés séparées par une virgule
    val livresCSV = livres.map(livre => s"${livre.titre},${livre.auteur},${livre.anneeDePublication},${livre.estEmprunte}").mkString("\n")
    try {
      val writer = new BufferedWriter(new FileWriter(cheminFichier))
      writer.write(livresCSV)
      writer.close()
      println(s"Bibliothèque sauvegardée dans $cheminFichier.")
    } catch {
      case e: Exception => println(s"Erreur lors de la sauvegarde : ${e.getMessage}")
    }
  }

  // Fonction pour charger la bibliothèque depuis un fichier CSV
  def chargerBibliothequeCSV(): Unit = {
    // try catch si jamais le fichier n'est pas retrouvé dans la directory resources
    try {
      val source = fromResource("biblio.csv")
      val livresList = ListBuffer.empty[Livre]

      source.getLines().foreach { line =>
        val fields = line.split(",")
        if (fields.length == 4) {
          val titre = fields(0)
          val auteur = fields(1)
          val anneeDePublication = fields(2).toInt
          val estEmprunte = fields(3).toBoolean

          val livre = new Livre(titre, auteur, anneeDePublication, estEmprunte)
          livresList += livre
        }
      }
      source.close()
      livres = livresList.toList
      println(s"Bibliothèque chargée.")
    } catch {
      case e: Exception => println(s"Erreur lors du chargement : ${e.getMessage}")
    }
  }

}
