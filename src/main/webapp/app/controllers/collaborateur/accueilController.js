var app = angular.module("lambda.bilan", ["ngCookies"]);

app.controller("accueilController",
    ['$scope','$cookies','$http', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $cookies,$http, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            idCollaborateur=51;
            $scope.user={nomUtilisateur :"Jaouad",prenomUtilisateur:"Elgharrasse"};
            $scope.errors={show:false,messages:[]};
            $scope.succes={show:false,message:''};


            $scope.objectifs=[];
            var listerObjectifs=function(){
                //chargement liste objectifs (annee en cours)
                var task = dao.getData("/collaborateurs/"+idCollaborateur+"/objectifs_revised", null, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.objectifs=result.data;
                    } else {
                        $scope.succes.show=false;
                        $scope.errors = {
                            title: properties.listerObjectifError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };

            listerObjectifs();

            $scope.rejeter=function(id){
                var task = dao.getData("/objectifs_refuser/"+id, null, HTTP_METHOD.put,{});
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.succes={show:true,message:result.data};
                        listerObjectifs();
                    } else {
                        $scope.succes.show=false;
                        $scope.errors = {
                            title: properties.rejeterObjectifError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };

        }])
;

