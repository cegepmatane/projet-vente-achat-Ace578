(function () {
    var instance = this;
    var numeroArticle;
    var initialiser = function (){

       this.articleDAO = new ArticleDAO();
       this.listeArticleDonnee = this.articleDAO.lister();

       window.addEventListener("hashchange", naviguer);

       naviguer();


    }   

     var naviguer = function() {

     var hash = window.location.hash;

     if (!hash) {
         var acceuilVue = new AcceuilVue();
         acceuilVue.afficher();
     }
     
    /* else if(hash.match(/^#personnaliser-article/)) {
             var ajouterArticleVue = new AjouterArticleVue(actionEnregistrerArticle);
             ajouterArticleVue.afficher();
     
          }
    else if(hash.match(/^#modifier-article/)) {
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