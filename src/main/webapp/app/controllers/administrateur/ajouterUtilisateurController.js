/**
 * Created by ST on 03/05/2014.
 */
var app = angular.module("lambda.bilan");




app.controller("ajouterUtilisateurController",
        ['$scope','$http','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,$http,security, HTTP_METHOD, properties , utils,dao ) {
            //datepicker model
            $scope.formData      = {};
            $scope.formData.dateNaissance = "";
            $scope.formData.dateEmbauche = "";

            $scope.actions.ajouter=function(){
                var dateNaissance=utils.filterDate($scope.formData.dateNaissance);
                var urlPhoto;
                var utilisateur={
                    nomUtilisateur:$scope.nom, prenomUtilisateur:$scope.prenom,
                    dateNaissanceUtilisateur:dateNaissance,
                    sexeUtilisateur:$scope.sexe,
                    telephoneUtilisateur:$scope.telephone,
                    adresseUtilisateur:$scope.adresse,
                    urlPhotoUtilisateur:urlPhoto,
                    emailUtilisateur:$scope.email
                };
                var url;
                switch($scope.role){
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
                console.log("-------"+url) ;
                console.log(angular.toJson(utilisateur));// on lance la requete
                var task=dao.getData(url,null,HTTP_METHOD.post,utilisateur);
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
                        // il y a eu des erreurs pour supprimer l'utilisateur
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.ajouterUtilisateurError;
                        $scope.errors.messages = utils.getErrors(result);

                    }
                });
            };
        }])
;

