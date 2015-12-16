/**
 * Created by ST on 03/05/2014.
 */
var app = angular.module("lambda.bilan", ["ngCookies","ngTable"]);



app.controller("gestionProjetController",
    ['$scope','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            //Check for security : si l'utilisateur est authentifié + autorisé on retourne l'utilisateur connecté
            // sinon on redirige vers la page login ou vers la page forbidden
            //       var user=security.check("administrateur");
            //      $scope.user=user;

            $scope.waiting={show:false};
            $scope.errors={show:false};
            $scope.tabledata={table:[]};
            $scope.tableParams = new NgTableParams({
                page: 1,            // show first page
                count: 10,          // count per page
                sorting: {
                    nomProjet: 'asc'     // initial sorting
                },
                filter: {
                    nomProjet: ''       // initial filter
                }
            },{
                total: $scope.tabledata.table.length, // length of data
                getData: function($defer, params) {
                    // use build-in angular filter
                    var filteredData = params.filter() ?
                        $filter('filter')($scope.tabledata.table, params.filter()) :
                        $scope.tabledata.table;

                    var orderedData = params.sorting() ?
                        $filter('orderBy')(filteredData, params.orderBy()) : filteredData;

                    params.total(filteredData.length);

                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            });




            //Action Lister les projets
            var lister=function() {
                //on récupere la tache de l'envoi de la requete
                var task = dao.getData(properties.urlProjet, null, HTTP_METHOD.get);
                //on attent la reponse...
                $scope.waiting.show = true;
                task.promise.then(function (result) {
                    // fin d'attente
                    $scope.waiting.show = false;
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur

                        $scope.tabledata.table = result.data;
                        $scope.tableParams.reload();
                    } else {
                        // il y a eu des erreurs pour obtenir l'agenda
                        //console.log("init fin att  "+angular.toJson(result));
                        $scope.errors = {

                            title: properties.listerUtilisateurError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }


                });
            };

            lister();
            //Action Supprimer un projet
            $scope.supprimer=function(id){
                    console.log("--- suppr");
                    var task=dao.getData(properties.urlProjet+"/"+id,null,HTTP_METHOD.delete);
                    //on attent la reponse
                    $scope.waiting.show = true;
                    task.promise.then(function(result){
                        // fin d'attente
                        $scope.waiting.show = false;
                        // erreur ?
                        if (result.err == 0) {
                            //Pas d'erreur
                            //console.log(angular.toJson(result.data));
                            console.log("---");
                            lister();
                        } else {
                            // il y a eu des erreurs pour supprimer l'utilisateur
                            $scope.errors = {
                                title:  properties.supprimerProjetError,
                                messages: utils.getErrors(result),
                                show: true};
                        }


                    });

            };

            $scope.ajouter=function(){
              var projet={
                  nomProjet:$scope.nomProjetAjouter,
                  evaluateur:{idUtilisateur:$scope.idEvaluateurAjouter}
              };

                console.log("--- ajouter");
                var task=dao.getData(properties.urlProjet+"/"+id,null,HTTP_METHOD.post,projet);
                //on attent la reponse
                $scope.waiting.show = true;
                task.promise.then(function(result){
                    // fin d'attente
                    $scope.waiting.show = false;
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        //console.log(angular.toJson(result.data));
                        console.log("---");
                        lister();
                    } else {
                        // il y a eu des erreurs pour supprimer l'utilisateur
                        $scope.errors = {
                            title:  properties.ajouterProjetError,
                            messages: utils.getErrors(result),
                            show: true};
                    }
                });
          };

            $scope.modifier=function(projet){
                $scope.idProjetModifier=projet.idProjet; //ce champs est caché dans html
                $scope.nomProjetModifier=projet.nomProjet;
                $scope.idEvaluateurModifier=projet.evaluateur.idUtilisateur;
            };

            $scope.validerModification=function(){

                var projet={
                    idProjet:$scope.idProjetModifier,
                    nomProjet:$scope.nomProjetModifier,
                    evaluateur:{idUtilisateur:$scope.idEvaluateurModifier}
                };

                console.log("--- modifier");
                var task=dao.getData(properties.urlProjet+"/"+id,null,HTTP_METHOD.put,projet);
                //on attent la reponse
                $scope.waiting.show = true;
                task.promise.then(function(result){
                    // fin d'attente
                    $scope.waiting.show = false;
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        //console.log(angular.toJson(result.data));
                        console.log("---");
                        lister();
                    } else {
                        // il y a eu des erreurs pour supprimer l'utilisateur
                        $scope.errors = {
                            title:  properties.supprimerProjetError,
                            messages: utils.getErrors(result),
                            show: true};
                    }


                });
            };



        }])
;

