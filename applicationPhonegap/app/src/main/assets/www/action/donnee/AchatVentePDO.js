var AchatVentePDO = (function() {

    return function() {

    var test;


    this.initialiserInstanceAjax = function(){
      this.req = null;
      if(window.XMLHttpRequest) {
        this.req = new XMLHttpRequest();
      }
      else if (window.ActiveXObject) {
      try {
        this.req = new ActiveXObject("Msxml2.XMLHTTP");
      } catch (e) {
         try {
          this.req = new ActiveXObject("Microsoft.XMLHTTP");
          } catch (e) {
              alert("XHR not created");
            }
        }
      }
    }

    this.getListeCategorie = function(){
        req = new XMLHttpRequest();
        req.open('GET', 'http://localhost/testAchatVente/listeCategorie.php', true); 
        req.onreadystatechange = function(){
          if(4 == req.readyState){
            var xml = req.responseXML;
            var leng = xml.getElementsByTagName("id");
            var listeCategorie = document.getElementById("theme");
            var li="";
            for(var i = 0; i< leng.length; i++){
              li += '<a href="#categorie/'+xml.getElementsByTagName("id")[i].childNodes[0].nodeValue+'"><button>'+xml.getElementsByTagName("nom")[i].childNodes[0].nodeValue+" </button ></a>";
              listeCategorie.innerHTML = li;
            }
            
          }
        }
        req.send(""); 
    }

    this.getListeArticle = function(categorie){
        req = new XMLHttpRequest();
        req.open('GET', 'http://localhost/testAchatVente/listeArticle.php?cat='+categorie, true); 
        req.onreadystatechange = function(){
          if(4 == req.readyState){
            document.getElementById("listearticletest").innerHTML = req.responseText;
          }
        }
        req.send(""); 
    }


    this.getArticle = function(id){
        alert(id);
        req = new XMLHttpRequest();
        req.open('GET', 'http://localhost/testAchatVente/Article.php?id='+id, true); 
        req.onreadystatechange = function(){
          if(4 == req.readyState){
            document.getElementById("articletest").innerHTML = req.responseText;
          }
        }
        req.send(""); 
    }



  };
})();
