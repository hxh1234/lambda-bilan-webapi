/**
 * Created by ST on 03/05/2014.
 */
var app = angular.module("lambda.bilan");




app.controller("modifierUtilisateurController",
        ['$scope','$http','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,$http ,security, HTTP_METHOD, properties , utils,dao ) {

            //datepicker model
            $scope.formData      = {};
            $scope.formData.dateNaissance = "";
            $scope.formData.dateEmbauche = "";
            var utilisateur;
            //  recuperer l'utilisateur depuis l'application
            // pour avoir toutes les infos afin de pouvoir modifier:
            $scope.actions.modifier=function(id,role){
                var task = dao.getData(properties.urlUtilisateur + "/" + id, null, HTTP_METHOD.get);
                //on attent la reponse
                task.promise.then(function (result) {
                    // fin d'attente de la requete
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        utilisateur=result.data;
                        utilisateur.role=role;
                        utils.fillScope($scope, utilisateur);
                        // return  result.data;
                    } else {
                        // il y a eu des erreurs pour recuperer l'utilisateur
                        $scope.errors = {
                            title: properties.recupererUtilisateurError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };
            //
            $scope.validerModification=function(){
                var dateNaissance=utils.filterDate($scope.formData.dateNaissance);
                var urlPhoto;
                utilisateur.nomUtilisateur=$scope.nom;
                utilisateur.prenomUtilisateur=$scope.prenom;
                utilisateur.dateNaissanceUtilisateur=dateNaissance;
                utilisateur.sexeUtilisateur=$scope.sexe;
                utilisateur.telephoneUtilisateur=$scope.telephone;
                utilisateur.adresseUtilisateur=$scope.adresse;
                utilisateur.urlPhotoUtilisateur=urlPhoto;
                utilisateur.emailUtilisateur=$scope.email;
                utilisateur.role=$scope.role;
                var url;
                switch(utilisateur.role){
                    case 'Administrateur':
                        url=properties.urlAdministrateur;
                        break;

                    case 'Collaborateur':
                        var managerRH={idUtilisateur:$scope.idManagerRH};
                        var dateEmbauche= utils.filterDate($scope.formData.dateEmbauche);
                        utilisateur.dateEmbaucheCollaborateur=dateEmbauche;
                        utilisateur.posteActuelCollaborateur=$scope.posteActuel;
                        utilisateur.managerRH=managerRH;
                        url=properties.urlCollaborateur;
                        break;

                    case 'Evaluateur':
                        url=properties.urlEvaluateur;
                        break;

                    case 'ManagerRH':
                        url=properties.urlManagerRH;
                        break;

                    default:
                        break;

                }
                // on lance la requete
                var task=dao.getData(url+"/"+utilisateur.idUtilisateur,null,HTTP_METHOD.put,utilisateur);
                //on attent la reponse
                task.promise.then(function(result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.errors.show = false;
                        $scope.succes.show=true;
                        $scope.succes.message=result.data;
                        $scope.actions.lister();
                    } else {
                        // il y a eu des erreurs pour modifier l'utilisateur
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.modifierUtilisateurError;
                        $scope.errors.messages = utils.getErrors(result);
                    }

                });

            };
      }])
;

