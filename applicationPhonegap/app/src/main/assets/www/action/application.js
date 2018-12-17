(function () {
    var instance = this;
    var numeroArticle;
    var cat;
    var idArticle;
    



    var achatVentePDO = new AchatVentePDO();
    
    //achatVentePDO.getListeArticle();



    var initialiser = function (){

       this.articleDAO = new ArticleDAO();
      // this.listeArticleDonnee = this.articleDAO.lister();
       window.addEventListener("hashchange", naviguer);

       naviguer();


    }   

    var naviguer = function() {

        var hash = window.location.hash;
        var nom;
        var adresseLivraison;
        var pays;
        var adresseCourriel;


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
             vueArticle.afficher(this.idArticle);
            achatVentePDO.getArticle(this.idArticle);
          }
    else if(hash.match(/^#personnalisation-sticker/)) {
            var href = location.href;
            this.idArticle = href.match(/([^\/]*)\/*$/)[1];
            var vuePersonnalisation = new VuePersonnalisationSticker();
            vuePersonnalisation.afficher();
            achatVentePDO.getListeSticker();

          }
    else if(hash.match(/^#personnalisation-information/)) {    
            var vuePersonnalisation = new VuePersonnalisationInformation();
            vuePersonnalisation.afficher();
            nom = document.getElementsByClassName("nom")[0].value;

            /*document.getElementsByClassName("nom")[0].value = nom;
            document.getElementsByClassName("adresseLivraison")[0].value = adresseLivraison;
            document.getElementsByClassName("pays")[0].value = pays;
            document.getElementsByClassName("adresseCourriel")[0].value = adresseCourriel;*/
    }


    else if(hash.match(/^#personnalisation-payement/)) {
            var vuePersonnalisationPayement = new VuePersonnalisationPayement();
            vuePersonnalisationPayement.afficher();
     
    }



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