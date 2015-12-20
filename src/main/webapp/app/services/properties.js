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
      rejeterObjectifError:"Les erreurs suivantes se sont produites lors du rejet de l'objectif",
      supprimerObjectifError:"Les erreurs suivantes se sont produites lors de la suppression de l'objectif",
      modifierObjectifError:"Les erreurs suivantes se sont produites lors de la modification de l'objectif",
      validerObjectifError:"Les erreurs suivantes se sont produites lors de la validation de l'objectif",
      affecterProjetError:"Les erreurs suivantes se sont produites lors de l'affectation des projets",
      ajouterObjectifsError:"Les erreurs suivantes se sont produites lors de l'ajout des objectifs",
      ajouterObjectifError:"Les erreurs suivantes se sont produites lors de l'ajout de l'objectif",
      recuperationsProjetsError:"Les erreurs suivantes se sont produites lors de la recuperation des projet au serveurs",
      recuperationsCategoriesError:"Les erreurs suivantes se sont produites lors de la recuperation des categories au serveurs",
      supprimerMesureError:"Les erreurs suivantes se sont produites lors de la suppression de la mesure",
      ajouterMesureError:"Les erreurs suivantes se sont produites lors de l'ajout de la mesure",
      modifierMesureError:"Les erreurs suivantes se sont produites lors de la modification de la mesure",
      listerObjectifError: "Les erreurs suivantes se sont produites lors du chargement de la liste des objectifs du collaborateur",
      recuperationsRespError:"Les erreurs suivantes se sont produites lors de la recuperation des resp. mesures au serveurs",
      validerBAPError:"Les erreurs suivantes se sont produites lors de la validation du BAP",
      listerBapError:"Les erreurs suivantes se sont produites lors du chargement  des BAP",
      listerFicheError:"Les erreurs suivantes se sont produites lors du chargement  de la fiche",
      listerFeedbackError:"Les erreurs suivantes se sont produites lors du chargement  des feedbacks",
      listerCollabError:"Les erreurs suivantes se sont produites lors du chargement  des collaborateurs",
      recuperationObjectifError:"Les erreurs suivantes se sont produites lors du chargement  des objectifs au serveur",
      recuperationEvaluateurError:"Les erreurs suivantes se sont produites lors du chargement  des evaluateurs au serveur",
      listerProjetError:"Les erreurs suivantes se sont produites lors du chargement des projets au serveur",
      supprimerProjetError:"Les erreurs suivantes se sont produites lors de la suppression du projet",
      modifierProjetError:"Les erreurs suivantes se sont produites lors de la modification du projet",
      recupererThemeError:"Les erreurs suivantes se sont produites lors du chargement des themes au serveur",
      recupererQualifError:"Les erreurs suivantes se sont produites lors du chargement des qualifications au serveur",
      recupererInterventionError:"Les erreurs suivantes se sont produites lors du chargement de l'intervention au serveur",
      enregistrerFeedbackError:"Les erreurs suivantes se sont produites lors de l'enregistrement de feedback",
      validerFeedbackError:"Les erreurs suivantes se sont produites lors de l'enregistrement de feedback",
      dresserPlanAmeliorationError:"Les erreurs suivantes se sont produites lors du dressage du plan d'amélioration",
      enregistrerIdAgendaError:"Les erreurs suivantes se sont produites lors de l'enregistrement de votre ID Google Agenda",
      changerMdpError:"Les erreurs suivantes se sont produites lors de la mise à jour de votre mot de passe",
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
      urlRespMesure:"/responsables_mesures",
      urlCategorie:"/categories",
      urlManagerRH:"/managerRHs",
      urlLogin:"/login",
      urlCollabsSansProjet:"/collaborateurs_without_projet",
      urlCollabsSansObj:"/collaborateurs_without_objectif",
      urlObjRefus:"/objectifs_refus",
      urlObjectif:"/objectifs",
      urlMesure:"/mesures",
      urlValiderObjectif:"/objectifs_valider",
      urlRefuserObjectif:"/objectifs_refuser",
      urlAffecterProjet:"/interventions",
      urlEvaluationObjectif:"/objectifs_evaluation",
      urlIntervention:"/interventions",
      urlTheme:"/themes",
      urlQualification:"/qualifications",
      urlPlanAmelioration:"/planAmeliorations",
      urlFeedback:"/feedbacks",
      // délai d'attente maximal pour les appels http en millisecondes
      timeout: 2000
    };
  })
;
