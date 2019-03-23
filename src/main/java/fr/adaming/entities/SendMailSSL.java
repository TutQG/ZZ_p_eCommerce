package fr.adaming.entities;

//J'ai crée une classe pour la fonction envoyer mais je pense que tu peux directement créer la fonction dans Client Service 
//vu que la classe n'a qu'un constructeur vide. 
public class SendMailSSL {

	public SendMailSSL() {
		super();
	}

	// La fonction peut renvoyer void, mais j'ai choisi de renvoyer un entier
	// pour faire un test d'envoie de mail plus tard.
	// On oublie pas le throws, qui va permettre à l'appli de ne pas crash si
	// l'adresse du destinataire est incorrecte
	public int sendMail(String destinataire, String message) throws Exception {
		// Ton mail, le mdp de ton mail, le destinataire, le sujet, et l'objet
		int a = 0;
		
		Mail.send("gbonnenf@gmail.com", "Lortiawhbo/140591", destinataire, "Résumé de votre commande", message);
		// Message et destinataire sont des variables afin d'envoyer le mail à
		// la bonne personne pour "destinataire", et de personnaliser
		// le message avec "message" selon la personne.

		a++;
		return a;
	}
}
