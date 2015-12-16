/**
 * Created by ST on 03/05/2014.
 */
var app = angular.module("lambda.bilan", ["ngCookies","ngTable",'ui.bootstrap']);




app.controller("gestionUtilisateurController",
        ['$scope','$http','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
            function ($scope,$http,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

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
                            nomUtilisateur: 'asc'     // initial sorting
                        },
                        filter: {
                            nomUtilisateur: ''       // initial filter
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

                //permet d'exclure les collaborateur abandonnées.
                $scope.filterCollab = function(utilisateur) {
                    if(utilisateur.etatCollaborateur==false)
                        return false;
                    return true;
                };



                //Action Lister les utilisateur
                var lister=function() {
                    //on récupere la tache de l'envoi de la requete
                    var task = dao.getData(properties.urlUtilisateur, null, HTTP_METHOD.get);
                    //on attent la reponse...
                    $scope.waiting.show = true;
                    task.promise.then(function (result) {
                        // fin d'attente
                        $scope.waiting.show = false;
                        // erreur ?
                        if (result.err == 0) {
                            //Pas d'erreur

                            $scope.utilisateurs = $scope.tabledata.table = result.data;
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

                //Action Supprimer un utilisateur
                var supprimer=function(id){
                        console.log("--- suppr");
                        var task=dao.getData(properties.urlUtilisateur+"/"+id,null,HTTP_METHOD.delete);
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
                                $scope.actions.lister();
                            } else {
                                // il y a eu des erreurs pour supprimer l'utilisateur
                                $scope.errors = {
                                    title:  properties.supprimerUtilisateurError,
                                    messages: utils.getErrors(result),
                                    show: true};
                            }


                        });

                };


                //Action abandonner un collaborateur (départ par exemple)
                var abandonner=function(id){
                        console.log("--- abandon");
                        var task=dao.getData("/utilisateur/"+id,null,HTTP_METHOD.put);
                        //on attent la reponse
                        $scope.waiting.show = true;
                        task.promise.then(function(result){
                            // fin d'attente
                            $scope.waiting.show = false;
                            // erreur ?
                            if (result.err == 0) {
                                $scope.actions.lister();
                            } else {
                                // il y a eu des erreurs pour supprimer l'utilisateur
                                $scope.errors = {
                                    title:  properties.abandonnerUtilisateurError,
                                    messages: utils.getErrors(result),
                                    show: true};
                            }


                        });

                };

                $scope.actions={ajouter:undefined,
                                modifier:undefined,
                                supprimer:supprimer,
                                abandonner:abandonner,
                                lister:lister};


                $scope.actions.lister();



                $scope.posteActuels=[
                    {code:'ICD1'},{code:'ICD2'},{code:'ICD3'}
                ];



            }])
;

