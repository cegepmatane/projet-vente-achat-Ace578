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
            var xml = req.responseXML;
            var leng = xml.getElementsByTagName("id");
            var listeArticle = document.getElementById("article");
            var li="";
            for(var i = 0; i< leng.length; i++){
              li += '<a href="#article/'+xml.getElementsByTagName("id")[i].childNodes[0].nodeValue+'"><img src="'+xml.getElementsByTagName("image")[i].childNodes[0].nodeValue+'" style="max-height:200px; max-width:200px;"></br>';
              listeArticle.innerHTML = li;
            }
          }
        }
        req.send(""); 
    }


    this.getArticle = function(id){
        req = new XMLHttpRequest();
        req.open('GET', 'http://localhost/testAchatVente/Article.php?id='+id, true); 
        req.onreadystatechange = function(){
          if(4 == req.readyState){
            var xml = req.responseXML;
            var leng = xml.getElementsByTagName("id");
            var listeArticle = document.getElementById("articletest");
              li = '<a href="#article/'+xml.getElementsByTagName("id")[0].childNodes[0].nodeValue+'"><img src="'+xml.getElementsByTagName("image")[0].childNodes[0].nodeValue+'" style="max-height:200px; max-width:200px;"></br><p>'+xml.getElementsByTagName("nom")[0].childNodes[0].nodeValue+' - '+xml.getElementsByTagName("prix")[0].childNodes[0].nodeValue +' euros</p>';
              listeArticle.innerHTML = li;
          }
        }
        req.send(""); 
    }

    this.getListeSticker = function(){
        req = new XMLHttpRequest();
        req.open('GET', 'http://localhost/testAchatVente/Sticker.php', true); 
        req.onreadystatechange = function(){
          if(4 == req.readyState){
            var xml = req.responseXML;
            var leng = xml.getElementsByTagName("id");
            var listeArticle = document.getElementById("tableSticker");
            var li="<tr><th></th><th>sticker</th><th>prix</th></tr>";
            for(var i = 1; i< (leng.length+1); i++){
              li += '<tr><td><form><input type="checkbox" name="sticker" id="checkbox'+i+'"></form></td><td>'+xml.getElementsByTagName("stickers")[i].childNodes[0].nodeValue +'</td><td>'+xml.getElementsByTagName("prix")[i-1].childNodes[0].nodeValue +'</td></tr>';
              listeArticle.innerHTML = li;
            }
          }
        }
        req.send(""); 
    }



  };
})();
