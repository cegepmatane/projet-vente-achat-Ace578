var Article = function (id,nom,marque,description) {

    this.id = id;
    this.nom = nom;
    this.marque = marque;
    this.description = description;

    this.setArticle = function(nouveauNom, nouvelleMarque, nouvelleDescription){
    	this.nom = nouveauNom;
    	this.marque = nouvelleMarque;
    	this.description = nouvelleDescription;
    }

}