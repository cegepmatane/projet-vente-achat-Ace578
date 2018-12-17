var VueArticle = (function() {

    var pageArticle = document.getElementById("page-article").innerHTML;

     return function() {



        this.afficher = function(id) {
		  document.getElementsByTagName("body")[0].innerHTML = pageArticle;
          var buttonPersonnaliser = document.getElementById("buttonPersonnaliser");
          li = '<a href="#personnalisation-sticker/'+id+'"><button type="submit">Personnaliser</button></a>'
          buttonPersonnaliser.innerHTML = li;

          



        }

        };

        /*window.onload = function(){ 
        	

		};*/
        

    


})();