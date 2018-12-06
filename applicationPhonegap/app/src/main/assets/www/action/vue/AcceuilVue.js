var AcceuilVue = (function() {

    var pageAcceuil = document.getElementById("page-acceuil").innerHTML;

     return function() {



        this.afficher = function() {
			document.getElementsByTagName("body")[0].innerHTML = pageAcceuil;


			var listeCategorie = document.getElementById("theme");

            var li="";
            for (var numeroArticle in listeArticleDonnee) {

                li += '<a href="#categorie/'+listeArticleDonnee[numeroArticle]+'"><button>'+listeArticleDonnee[numeroArticle].nom+" </button></a>";

            }
            listeCategorie.innerHTML = li;

           }
        };

        window.onload = function(){ 
        	document.getElementById("buttonHomme").onclick = function() {myFunction()};;
			function myFunction() {
			    document.getElementById("buttonHomme").style.background = "blue";
			    document.getElementById("buttonFemme").style.background = "white";
			}
			document.getElementById("buttonFemme").onclick = function() {myFunction()};;
			function myFunction() {
			    document.getElementById("buttonHomme").style.background = "white";
			    document.getElementById("buttonFemme").style.background = "red";
			}

		};
        

    


})();
