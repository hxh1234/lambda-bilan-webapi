/**
 * Created by ST on 05/05/2014.
 */
angular.module("lambda.bilan")
  .factory('utils', ['properties','$filter', function (properties,$filter) {

    function filterDate(date){
      return $filter('date')(date,'yyyy-MM-dd');
    }

    // analyse des erreurs dans la réponse du serveur JSON
    function getErrors(data) {
      // data {err:n, messages:[]}, err!=0
      // erreurs
      var errors = [];
      // code d'erreur
      var err = data.err;
      switch (err) {
        case 2 :
          // not authorized
          errors.push(properties.not_authorized);
          break;
        case 3 :
          // forbidden
          errors.push(properties.forbidden);
          break;
        case 4 :
          // erreur locale
          errors.push(properties.not_http_error);
          break;
        case 6 :
          // document non trouvé
          errors.push(properties.not_found);
          break;
        default :
          // autres cas (des erreurs provenants de l'application)
          errors = data.messages;
          break;
      }
      // si pas de msg, on en met un
      if (!errors || errors.length == 0) {
        errors = [properties.error_unknown];
      }
      // on rend la liste des erreurs
      return errors;
    }

    function redirectTo(page){
      $window.location.href(properties.urlServer+""+page);
    }

    function clearScope(scope){

        scope.nom="";
        scope.prenom="";
        scope.dateNaissance="";
        scope.dateEmbauche="";
        scope.adresse="";
        scope.email="";
        scope.telephone="";
        scope.sexe="";
        scope.posteActuel="";
        scope.idManagerRH="";
        scope.role="";
    }

    function fillScope(scope,utilisateur){
          scope.nom=utilisateur.nomUtilisateur;
          scope.prenom=utilisateur.prenomUtilisateur;
          scope.formData.dateNaissance=utilisateur.dateNaissanceUtilisateur;
          scope.formData.dateEmbauche=utilisateur.dateEmbaucheCollaborateur;
          scope.adresse=utilisateur.adresseUtilisateur;
          scope.email=utilisateur.emailUtilisateur;
          scope.telephone=utilisateur.telephoneUtilisateur;
          scope.sexe=utilisateur.sexeUtilisateur;
          scope.posteActuel=utilisateur.posteActuelCollaborateur;
          scope.idManagerRH=utilisateur.idManagerRH;
          scope.role=utilisateur.role;
    }




    // instance du service
    return {
      getErrors: getErrors,
      redirectTo:redirectTo,
      filterDate:filterDate,
      fillScope:fillScope
    };
  }]);
