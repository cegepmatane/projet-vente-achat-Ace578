var AcceuilVue = (function() {

    var pageAcceuil = document.getElementById("page-acceuil").innerHTML;

     return function() {

		var identifiant;

        this.afficher = function(cat) {
			document.getElementsByTagName("body")[0].innerHTML = pageAcceuil;


			var listeCategorie = document.getElementById("theme");

            var li="";
            for (var numeroCategorie in listeCategorieDonnee) {

                li += '<a href="#categorie/'+listeCategorieDonnee[numeroCategorie].id+'"><button>'+listeCategorieDonnee[numeroCategorie].nom+" </button ></a>";
                
            listeCategorie.innerHTML = li;
			}
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
