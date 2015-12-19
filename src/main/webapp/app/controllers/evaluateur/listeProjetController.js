var app = angular.module("lambda.bilan", ["ngCookies","ngTable"]);

app.controller("listeProjetController",
    ['$scope','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            var idEvaluateur=10;

            $scope.user={nomUtilisateur :"Jaouad",prenomUtilisateur:"Elgharrasse"};
            $scope.errors={show:false,messages:[]};
            $scope.succes={show:false,message:''};

            $scope.tabledata={table:[]};
            $scope.tableParams = new NgTableParams({
                page: 1,            // show first page
                count: 10,          // count per page
                sorting: {
                    idProjet: 'asc'     // initial sorting
                },
                filter: {
                    idProjet: ''       // initial filter
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



            var task = dao.getData("/evaluateurs/"+idEvaluateur+"/projets", null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur

                    $scope.tabledata.table = result.data;
                    $scope.tableParams.reload();
                } else {
                    // il y a eu des erreurs pour obtenir l'agenda
                    $scope.succes.show=false;
                    $scope.errors = {

                        title: properties.listerProjetError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }


            });

            //liste des collabs
            $scope.collaborateurs=[];
            $scope.projet={};
            $scope.listerCollab=function(projet){
                $scope.projet=projet;
                var task = dao.getData("/projets/"+projet.idProjet+"/collaborateurs", null, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.collaborateurs = result.data;
                    } else {
                        // il y a eu des erreurs pour obtenir l'agenda
                        $scope.succes.show=false;
                        $scope.errors = {
                            title: properties.listerCollabError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };

            $scope.collaborateur={};
            $scope.setCollaborateur=function(collaborateur){
                $scope.collaborateur=collaborateur;
            };


            $scope.intervention={};
            $scope.ajouterIntervention=function(){
                $scope.intervention.projet={idProjet:$scope.projet.idProjet};
                $scope.intervention.collaborateur={idUtilisateur:$scope.collaborateur.idUtilisateur};
                $scope.intervention.notes=[];
                console.log(angular.toJson($scope.intervention));
                var task = dao.getData(properties.urlIntervention+"/"+$scope.projet.idProjet+"/"+$scope.collaborateur.idUtilisateur,
                    null, HTTP_METHOD.put,$scope.intervention);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.succes={show:true,message:result.data};
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors = {
                            title: properties.listerCollabError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };

            $scope.action={creerFeedback:undefined};

        }])
;

