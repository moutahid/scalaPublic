package classes

// Définition de la classe Livre
class Livre(val titre: String, val auteur: String, val anneeDePublication: Int, var estEmprunte: Boolean = false) {

  // Fonction pour emprunter le livre
  def emprunter(): Unit = {
    // Vérifie si le livre n'est pas déjà emprunté
    if (!estEmprunte) {
      estEmprunte = true
      // Affiche un message indiquant que le livre a été emprunté
      println(s"Le livre \"$titre\" a été emprunté.")
    } else {
      // Affiche un message indiquant que le livre est déjà emprunté
      println(s"Le livre \"$titre\" est déjà emprunté.")
    }
  }

  // Fonction pour rendre le livre
  def rendre(): Unit = {
    // Vérifie si le livre est actuellement emprunté
    if (estEmprunte) {
      estEmprunte = false
      // Affiche un message indiquant que le livre a été rendu
      println(s"Le livre \"$titre\" a été rendu.")
    } else {
      // Affiche un message indiquant que le livre n'est pas emprunté
      println(s"Le livre \"$titre\" n'est pas emprunté.")
    }
  }
}

