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
                $scope.user={nomUtilisateur :"Jaouad",prenomUtilisateur:"Elgharrasse"};

            $scope.succes={show:false,message:''};
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
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.tabledata.table = result.data;
                        $scope.tableParams.reload();
                    } else {
                        $scope.succes.show=false;
                        $scope.errors = {
                            title: properties.listerProjetError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };
            lister();
            //recuperer les evaluateurs!
            $scope.evaluateurs=[];
            var task = dao.getData(properties.urlEvaluateur, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.evaluateurs=result.data;
                } else {
                    // il y a eu des erreurs pour obtenir l'agenda
                    //console.log("init fin att  "+angular.toJson(result));
                    $scope.succes.show=false;
                    $scope.errors = {
                        title: properties.recupererEvaluateurError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });


            //Action Supprimer un projet
            $scope.supprimer=function(id){
                    var task=dao.getData(properties.urlProjet+"/"+id,null,HTTP_METHOD.delete);
                    //on attent la reponse
                    task.promise.then(function(result){
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            $scope.errors.show=false;
                            $scope.succes={show:true,message:result.data};
                            lister();
                        } else {
                            // il y a eu des erreurs pour supprimer l'utilisateur
                            $scope.succes.show=false;
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
                var task=dao.getData(properties.urlProjet,null,HTTP_METHOD.post,projet);
                //on attent la reponse
                task.promise.then(function(result){
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.errors.show=false;
                        $scope.succes={show:true,message:result.data};
                        lister();
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors = {
                            title:  properties.ajouterProjetError,
                            messages: utils.getErrors(result),
                            show: true};
                    }
                });
          };

            $scope.modifier=function(projet){
                $scope.idProjetModifier=projet.idProjet;
                $scope.nomProjetModifier=projet.nomProjet;
                $scope.idEvaluateurModifier=projet.idEvaluateur;
            };

            $scope.validerModification=function(){

                var projet={
                    idProjet:$scope.idProjetModifier,
                    nomProjet:$scope.nomProjetModifier,
                    evaluateur:{idUtilisateur:$scope.idEvaluateurModifier}
                };

                console.log("--- modifier");
                var task=dao.getData(properties.urlProjet+"/"+$scope.idProjetModifier,null,HTTP_METHOD.put,projet);
                //on attent la reponse
                task.promise.then(function(result){
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.errors.show=false;
                        $scope.succes={show:true,message:result.data};
                        lister();
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors = {
                            title:  properties.modifierProjetError,
                            messages: utils.getErrors(result),
                            show: true};
                    }
                });
            };



        }])
;

