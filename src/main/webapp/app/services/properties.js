/**
 * Created by ST on 05/05/2014.
 */
angular.module("lambda.bilan")
  .factory('properties', function () {
    return {
      //Error Messages
      listerUtilisateurError: "Les erreurs suivantes se sont produites lors du chargement de la liste des utilisateurs",
      supprimerUtilisateurError:"Les erreurs suivantes se sont produites lors de la suppression de l'utilisateur",
      ajouterUtilisateurError:"Les erreurs suivantes se sont produites lors de l'ajout de l'utilisateur",
      modifierUtilisateurError:"Les erreurs suivantes se sont produites lors de la modification de l'utilisateur",
      récupérerUtilisateurError:"Les erreurs suivantes se sont produites lors de la récupération de l'utilisateur au serveur",
      abandonnerUtilisateurError:"Les erreurs suivantes se sont produites lors de l'abandon du collaborateur ",
      listerCollabsSansProjetError:"Les erreurs suivantes se sont produites lors du chargement de la liste " +
                                            "des collaborateurs sans projet ",
      listerCollabsSansObjError:"Les erreurs suivantes se sont produites lors du chargement de la liste " +
      "des collaborateurs sans objectif ",
      listerObjRefusError:"Les erreurs suivantes se sont produites lors du chargement de la liste " +
      "des collaborateurs sans objectif ",
      supprimerObjectifError:"Les erreurs suivantes se sont produites lors de la suppression de l'objectif",
      modifierObjectifErrors:"Les erreurs suivantes se sont produites lors de la modification de l'objectif",
      validerObjectifError:"Les erreurs suivantes se sont produites lors de la validation de l'objectif",
      affecterProjetError:"Les erreurs suivantes se sont produites lors de l'affectation des projets",
      ajouterObjectifsErrors:"Les erreurs suivantes se sont produites lors de l'ajout des objectifs",
      recuperationsProjetsErrors:"Les erreurs suivantes se sont produites lors de la recuperation des projet au serveurs",
      recuperationsCategoriesErrors:"Les erreurs suivantes se sont produites lors de la recuperation des categories au serveurs",

      //Erreurs http
      error_unknown: "Erreur non identifiée",
      not_authorized: "Erreur d'authentification",
      forbidden: "Accès refusé",
      not_http_error: "L'accès au serveur n'a pu être réalisé",
      not_found: "Document non trouvé sur le serveur",
      //Server URL
      urlServer:"http://localhost:8080",
      //Pages URL
      forbidden:"",
      login:"/login",
      // Rest API URL:
      urlUtilisateur:"/utilisateurs",
      urlCollaborateur:"/collaborateurs",
      urlEvaluateur:"/evaluateurs",
      urlAdministrateur:"/administrateurs",
      urlProjet:"/projets",
      urlCategorie:"/categories",
      urlManagerRH:"/managerRHs",
      urlLogin:"/login",
      urlCollabsSansProjet:"/collaborateurs_without_projet",
      urlCollabsSansObj:"/collaborateurs_without_objectif",
      urlObjRefus:"/objectifs_refus",
      urlObjectif:"/objectifs",
      urlValiderObjectif:"/objectifs_valider",
      urlRefuserObjectif:"/objectifs_refuser",
      urlAffecterProjet:"/interventions",





      // délai d'attente maximal pour les appels http en millisecondes
      timeout: 2000
    };
  })
;
