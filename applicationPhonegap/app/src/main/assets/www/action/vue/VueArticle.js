var VueArticle = (function() {

    var pageArticle = document.getElementById("page-article").innerHTML;

     return function() {



        this.afficher = function() {
		  document.getElementsByTagName("body")[0].innerHTML = pageArticle;
          

            



        }

        };

        /*window.onload = function(){ 
        	

		};*/
        

    


})();