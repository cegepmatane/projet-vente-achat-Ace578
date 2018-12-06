var VueListeArticle = (function() {

    var pageListeArticle = document.getElementById("page-liste-article").innerHTML;

     return function() {



        this.afficher = function() {
		  document.getElementsByTagName("body")[0].innerHTML = pageListeArticle;

        



        }

        };

        /*window.onload = function(){ 
        	

		};*/
        

    


})();