<?php
	header ("Content-Type:text/xml");


	require_once ('ConnexionBaseDeDonnee.php');

	$connexion = new ConnexionBaseDeDonnee();
	$baseDeDonnees = $connexion->getBaseDeDonnees();

	$categorie = $_GET["cat"];
	$SQL_LISTE_ACHAT = "SELECT * FROM produit where id_categorie=".$categorie.";";
	$resultatListeAchat = $baseDeDonnees->prepare($SQL_LISTE_ACHAT);
	$resultatListeAchat->execute();
	$listeAchat = $resultatListeAchat->fetchAll(PDO::FETCH_OBJ);		
	


	echo '<?xml version="1.0" encoding="ISO-8859-1"?>
	<articles>';
	foreach($listeAchat as $article)
	{
	echo "<article>";
		echo "<id>" . $article->id . "</id>";
		echo "<nom>" . $article->nom . "</nom>";
		echo "<image>" . $article->image . "</image>";
		echo "<prix>" . $article->prix . "</prix>";
		echo "<id_categorie>" . $article->id_categorie . "</id_categorie>";
	echo "</article>";
	 }
	echo "</articles>";


?>