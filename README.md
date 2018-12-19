# projet-vente-achat-Ace578
projet-vente-achat-Ace578 

Ace578 : BALTZ Eliott

Lien du dossier fonctionnel : https://docs.google.com/document/d/1yKiszbRdPJfhPIyVAYIiasKMY8hEPlFoADdaKkj8W3A/edit?usp=sharing

Base de données SQL : MariaDB

Base de données NoSQL : MongoDB

NoSQL utilisé dans le volet de gestion actuellement (SQL en commentaire et fonctionnel).

SQL utilisé dans le volet de statistiques.

Archivage des données avec des tables achat_"année" : achat_2018, achat_2017...

Redis utilisé pour les requêtes statistique : Quand le timestamp stocké dans le redis à la clé "timestamp" date de plus d'une heure, l'utilisation de la bd se fait avec un reset de la clé "timestamp" et des différentes requêtes dans le redis. Si la clé "timestamp" est récente, on utilise les données stockées dans redis (en JSON).

Optimisation SQL : * dans les SELECT utilisés uniquement quand c'est nécessaire puisqu'on récupère tout. 
                   Dans les conditions WHERE on mettait en premier la condition la plus filtrante, maintenant on a plus qu'un where puisqu'on a fait l'archivage de nos tables 
