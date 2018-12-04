var ArticleDAO = function() {

    var listeArticle;

    var initialiser = function () {

        if(!listeArticle) {

            listeArticle=[];

        }

    }

    this.chercherAvecId = function(idRecherche) {
        for (position in listeArticle){
            if(listeArticle[position].id == idRecherche) {
                return listeArticle[position];
            }
        }


    }

    this.ajouter = function(article) {
        if (listeArticle.length > 0) {
            article.id = listeArticle[listeArticle.length-1].id + 1;
        }
        else {
            article.id = 0;
        }
        listeArticle[article.id] = article;
        localStorage['article'] = JSON.stringify(listeArticle);



    }

    this.lister = function() {


           if (localStorage['article']) {
               listeArticle = JSON.parse(localStorage['article']);
           }

           for(position in listeArticle){


                var article = new Article(listeArticle[position].id,
                                        listeArticle[position].nom,
                                        listeArticle[position].marque,
                                        listeArticle[position].description
                );
                listeArticle[position] = article;
           }
           return listeArticle;

    }

    this.modifier = function(id, article){
        listeArticle[id] = article;
    }


    initialiser();



}