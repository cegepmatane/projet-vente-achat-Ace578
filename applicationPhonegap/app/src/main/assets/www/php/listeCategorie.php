<?php
	header ("Content-Type:text/xml");



	require_once ('ConnexionBaseDeDonnee.php');

	$connexion = new ConnexionBaseDeDonnee();
	$baseDeDonnees = $connexion->getBaseDeDonnees();

	$SQL_LISTE_ACHAT = "SELECT * FROM categorie;";
	$resultatListeAchat = $baseDeDonnees->prepare($SQL_LISTE_ACHAT);
	$resultatListeAchat->execute();
	$listeAchat = $resultatListeAchat->fetchAll(PDO::FETCH_OBJ);		

/*<categories>
<?php foreach($listeAchat as $categorie) {?>
<categorie>
	<id><?=$categorie->id?></id>
	<nom><?=$categorie->nom?></nom>
	<image><?=$categorie->image?></image>
</categorie>
<?}?>
</categories>*/


echo '<?xml version="1.0" encoding="ISO-8859-1"?>
<categories>';
foreach($listeAchat as $categorie)
{
echo "<categorie>";
	echo "<id>" . $categorie->id . "</id>";
	echo "<nom>" . $categorie->nom . "</nom>";
	echo "<image>" . $categorie->image . "</image>";
echo "</categorie>";
 }
echo "</categories>";

?>




