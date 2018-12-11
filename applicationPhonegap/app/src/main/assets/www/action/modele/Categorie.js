var Categorie = function (id,nom,description) {

    this.id = id;
    this.nom = nom;
    this.description = description;

    this.setArticle = function(nouveauNom, nouvelleMarque, nouvelleDescription){
    	this.nom = nouveauNom;
    	this.description = nouvelleDescription;
    }

}