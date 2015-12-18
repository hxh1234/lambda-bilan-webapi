var app = angular.module("lambda.bilan", ["ngCookies","ngTable","isteven-multi-select"]);

app.controller("listCollaborateurController",
    ['$scope','$http','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,$http,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ) {


            var idManagerRH=1;
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


            var task = dao.getData("/managerRHs/"+idManagerRH+"/collaborateurs", null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.tabledata.table = result.data;
                    $scope.tableParams.reload();
                } else {
                    $scope.errors = {
                        title: properties.listerCollabError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.evaluerObjectifs=function(collaborateur){
                $cookies.setObject("",null);
                utils.redirectTo("");

            };
            $scope.afficherBAPs=function(collaborateur){
                $cookies.setObject("",null);
                utils.redirectTo("");
            };


            $scope.action={affecterProjet:undefined};



            $scope.objectifsInput=[];
            var task = dao.getData(properties.urlProjet, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreurs
                    console.log("===>"+angular.toJson(result.data));
                    $scope.projetsInput=result.data;
                    for(var i=0;i<$scope.objectifsInput.length;i++){
                        $scope.objectifsInput[i].name=$scope.objectifsInput[i].nomProjet;
                        $scope.objectifsInput[i].maker="  : "+$scope.objectifsInput[i].nomEvaluateur;
                    }
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recuperationObjectifError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.validerPlan=function(){
                $scope.duree;
                $scope.moyens;

            };

            $scope.validerAction=function(){

                
            };


        }])
;


