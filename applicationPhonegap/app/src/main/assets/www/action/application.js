(function () {
    var instance = this;
    var numeroArticle;
    var cat;
    var idArticle;
    



    var achatVentePDO = new AchatVentePDO();
    
    //achatVentePDO.getListeArticle();


    this.listeCategorieDonnee = [new Categorie(1,"casquette","nouveau modele"),
                                new Categorie(2 ,"manteau","nouveau modele")];

    this.listeArticleDonnee = [new Article(0,"swift","nike","nouveau modele",1,"image/casquetteBaseball.jpg"),
                                new Article(1,"doudoune","lacoste","nouveau modele",2,"image/doudoune1.jpg"),
                                new Article(2,"blueberry","lacoste","nouveau modele",1,"image/casquetteLondon.png"),
                                new Article(3,"doudoune","lacoste","nouveau modele",2,"image/doudoune2.jpg")];



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
        achatVentePDO.getListeCategorie();
     }
     
    else if(hash.match(/^#categorie/)) {
            var href = location.href;
             this.cat = href.match(/([^\/]*)\/*$/)[1];
             var vueListeArticle = new VueListeArticle();
             vueListeArticle.afficher(this.cat); 
             achatVentePDO.getListeArticle(this.cat);

          }
    else if(hash.match(/^#article/)) {
            var href = location.href;
            this.idArticle = href.match(/([^\/]*)\/*$/)[1];
             var vueArticle = new VueArticle();
             vueArticle.afficher();
            achatVentePDO.getArticle(this.idArticle);
          }
    else if(hash.match(/^#personnalisation/)) {
             var vuePersonnalisation = new VuePersonnalisation();
             vuePersonnalisation.afficher();
            achatVentePDO.getArticle(this.idArticle);
            achatVentePDO.getSticker();
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