<?php
	header ("Content-Type:text/xml");

	header("Access-Control-Allow-Origin: *");

	require_once ('ConnexionBaseDeDonnee.php');

	$connexion = new ConnexionBaseDeDonnee();
	$baseDeDonnees = $connexion->getBaseDeDonnees();

	$SQL_LISTE_ACHAT = "SELECT * FROM personnalisation;";
	$resultatListeAchat = $baseDeDonnees->prepare($SQL_LISTE_ACHAT);
	$resultatListeAchat->execute();
	$listeAchat = $resultatListeAchat->fetchAll(PDO::FETCH_OBJ);		
	
	
	echo '<?xml version="1.0" encoding="ISO-8859-1"?>
	<stickers>';
	foreach($listeAchat as $sticker)
	{
		echo "<sticker>";
		echo "<id>" . $sticker->id . "</id>";
		echo "<stickers>" . $sticker->stickers . "</stickers>";
		echo "<prix>" . $sticker->prix . "</prix>";
		echo "</sticker>";
	 }
	echo "</stickers>";

	


?>