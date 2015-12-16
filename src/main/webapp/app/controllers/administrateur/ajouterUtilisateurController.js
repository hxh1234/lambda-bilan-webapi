/**
 * Created by ST on 03/05/2014.
 */
var app = angular.module("lambda.bilan");




app.controller("ajouterUtilisateurController",
        ['$scope','$http','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,$http,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ) {
            //datepicker model
            $scope.formData      = {};
            $scope.formData.dateNaissance = "";
            $scope.formData.dateEmbauche = "";

            $scope.actions.ajouter=function(){
                console.log("init ajouter fct");
                var dateNaissance=utils.filterDate($scope.formData.dateNaissance);
                var urlPhoto;

                var utilisateur={
                    nomUtilisateur:$scope.nom, prenomUtilisateur:$scope.prenom,
                    dateNaissanceUtilisateur:dateNaissance,
                    sexeUtilisateur:$scope.sexe, telephoneUtilisateur:$scope.telephone,
                    adresseUtilisateur:$scope.adresse, urlPhotoUtilisateur:urlPhoto,
                    emailUtilisateur:$scope.email
                };

                console.log($scope.telephone+" -- "+$scope.email);
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

                console.log("==>"+angular.toJson(utilisateur));
                // on lance la requete
                var task=dao.getData(url,null,HTTP_METHOD.post,utilisateur);
                //on attent la reponse
                $scope.waiting.show = true;
                task.promise.then(function(result) {
                    // fin d'attente
                    $scope.waiting.show = false;
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        console.log(angular.toJson(result.data));
                        $scope.actions.lister();

                    } else {
                        // il y a eu des erreurs pour supprimer l'utilisateur
                        $scope.errors = {
                            title: properties.ajouterUtilisateurError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }


                });





            };

            




        }])
;

