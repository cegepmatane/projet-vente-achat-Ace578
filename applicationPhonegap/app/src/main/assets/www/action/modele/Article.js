var Article = function (id,nom,marque,description,categorie,image) {

    this.id = id;
    this.nom = nom;
    this.marque = marque;
    this.description = description;
    this.categorie = categorie;
    this.image = image;

    this.setArticle = function(nouveauNom, nouvelleMarque, nouvelleDescription){
    	this.nom = nouveauNom;
    	this.marque = nouvelleMarque;
    	this.description = nouvelleDescription;
    }

}