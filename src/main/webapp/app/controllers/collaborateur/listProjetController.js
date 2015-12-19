var app = angular.module("lambda.bilan", ["ngCookies"]);

app.controller("listeProjetController",
    ['$scope','$cookies','$http', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $cookies,$http, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            idCollaborateur=51;
            $scope.user={nomUtilisateur :"Jaouad",prenomUtilisateur:"Elgharrasse"};
            $scope.errors={show:false,messages:[]};
            $scope.succes={show:false,message:''};


            $scope.projets=[];
            //chargement liste collabs
            var task = dao.getData("/collaborateurs/"+idCollaborateur+"/projets", null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.projets=result.data;
                } else {
                    $scope.succes.show=false;
                    $scope.errors = {
                        title: properties.listerProjetError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

        }])
;


