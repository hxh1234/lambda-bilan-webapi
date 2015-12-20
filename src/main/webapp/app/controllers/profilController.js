/**
 * Created by jaouad-su on 19/12/2015.
 */

var app = angular.module("lambda.bilan", ["ngCookies"]);

app.controller("profilController",
    ['$scope','$cookies','$http','security','HTTP_METHOD','properties', 'utils','dao','fileUpload',
        function ($scope,$cookies,$http ,security, HTTP_METHOD, properties , utils,dao,fileUpload ) {


            $scope.succes={show:false,message:''};
            $scope.errors={show:false,messages:[]};
            idUtilisateur=51;
            $scope.utilisateur;
            var task = dao.getData("/utilisateurs/"+idUtilisateur, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.utilisateur=result.data;
                } else {
                    $scope.errors = {
                        title: properties.listerBapError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.enregistrerIdAgenda= function () {
                var task = dao.getData("/utilisateurs/" +idUtilisateur , null, HTTP_METHOD.put,{idCalendrierUtilisateur:$scope.idAgenda});
                //on attent la reponse
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.errors.show = false;
                        $scope.succes = {show: true, message: result.data};
                    } else {
                        // il y a eu des erreurs pour supprimer l'utilisateur
                        $scope.succes.show = false;
                        $scope.errors = {
                            title: properties.enregistrerIdAgendaError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };

            $scope.changerMdp=function () {
                var model={
                    idUtilisateur:idUtilisateur,
                    currentPassword:$scope.currentMdp,
                    newPassword:$scope.newMdp
                };
                var task = dao.getData("/profils", null, HTTP_METHOD.put,model);
                //on attent la reponse
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.errors.show = false;
                        $scope.succes = {show: true, message: result.data};
                    } else {
                        // il y a eu des erreurs pour supprimer l'utilisateur
                        $scope.succes.show = false;
                        $scope.errors = {
                            title: properties.changerMdpError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };


            $scope.uploadFile = function() {
                var file = $scope.myFile;
                console.log('file is ');
                console.log(file );
                var uploadUrl = properties.urlServer+"/fileUpload";
                fileUpload.uploadFileToUrl(file, uploadUrl);
            };




       }])
;

