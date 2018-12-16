var VueListeArticle = (function() {

    var pageListeArticle = document.getElementById("page-liste-article").innerHTML;

     return function() {



        this.afficher = function(cat) {
		  document.getElementsByTagName("body")[0].innerHTML = pageListeArticle;

          var listeArticle = document.getElementById("article");

            var li="";
            for (var numeroCategorie in listeArticleDonnee) { 
                if(listeArticleDonnee[numeroCategorie].categorie == cat){
                    li += '<a href="#article/'+listeArticleDonnee[numeroCategorie].id+'"><img src="'+listeArticleDonnee[numeroCategorie].image+'" style="max-height:200px; max-width:200px;"></br>';
                }
            }
            listeArticle.innerHTML = li;

            



        }

        };

        /*window.onload = function(){ 
        	

		};*/
        

    


})();