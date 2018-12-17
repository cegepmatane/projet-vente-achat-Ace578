<?php

header("Access-Control-Allow-Origin: *");

class ConnexionBaseDeDonnee{
	private $baseDeDonnees = NULL;
	
	public function __construct(){
		try{
			$this->baseDeDonnees = new PDO('mysql:host=158.69.192.249;dbname=vente','vendeur','password');
			$this->baseDeDonnees->query('SET NAMES utf8');
			$this->baseDeDonnees->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);}
		catch(PDOException $e){
			die('<p>erreur connection'. $e->getMessage(). '</p>');
		}
	}
	
	
	public function getBaseDeDonnees(){
		return $this->baseDeDonnees;
	}
}
?>