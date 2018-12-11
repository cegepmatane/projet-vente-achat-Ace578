(function () {
    var instance = this;
    var numeroArticle;
    var cat;
    this.listeCategorieDonnee = [new Categorie(0,"casquette","nouveau modele"),
                                new Categorie(1,"manteau","nouveau modele")];

    this.listeArticleDonnee = [new Article(0,"swift","nike","nouveau modele",0,"casquetteBaseball.jpg"),
                                new Article(1,"doudoune","lacoste","nouveau modele",1,"doudoune1.jpg"),
                                new Article(1,"blueberry","lacoste","nouveau modele",0,"casquetteLondon.png"),
                                new Article(1,"doudoune","lacoste","nouveau modele",1,"doudoune2.jpg")];



    var initialiser = function (){

       this.articleDAO = new ArticleDAO();
      // this.listeArticleDonnee = this.articleDAO.lister();
       window.addEventListener("hashchange", naviguer);

       naviguer();


    }   

     var naviguer = function() {

     var hash = window.location.hash;

     if (!hash) {
         var acceuilVue = new AcceuilVue();
         acceuilVue.afficher();
     }
     
    else if(hash.match(/^#categorie/)) {
            var href = location.href;
             this.cat = href.match(/([^\/]*)\/*$/)[1];
             var vueListeArticle = new VueListeArticle();
             vueListeArticle.afficher(this.cat); 
             
          }
    else if(hash.match(/^#categorie/[0-9])) {
             var vueArticle = new VueArticle();
             vueArticle.afficher();  
          }

    /*else if(hash.match(/^#modifier-article/)) {
             var modifierArticleVue = new ModifierArticleVue(actionEnregistrerArticle);
             modifierArticleVue.afficher(numeroArticle);
     
          }*/

     

     }

     /*var actionEnregistrerArticle = function (article) {
        this.articleDAO.ajouter(article);
        naviguerAccueil();
     }

     var actionModifierArticle = function () {
        naviguerAccueil();
     }

     var naviguerAccueil = function () {
        window.location.hash = "";

     }*/

     initialiser();

})();