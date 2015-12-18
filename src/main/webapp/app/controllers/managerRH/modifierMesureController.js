var app = angular.module("lambda.bilan");



app.controller("modifierMesureController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {


            var idObjectif;
            var idMesure;
            $scope.action.modifierMesure=function(mesure,idObjectif){
                $scope.comment=mesure.commentMesurer;
                $scope.poids=mesure.poidsMesure;
                $scope.resultat=mesure.resultatMesure;
                $scope.mode=mesure.modeAccesMesure;
                $scope.idResp= mesure.responsableMesure.idResponsableMesure;
                idObjectif=mesure.idObjectif;
                idMesure=mesure.idMesure;
            };

            $scope.modifierMesure=function(){
                var mesure= {
                    "idMesure":idMesure,
                    "commentMesurer":$scope.comment,
                    "poidsMesure":$scope.poids,
                    "resultatMesure":$scope.resultat,
                    "modeAccesMesure":$scope.mode,
                    "responsableMesure":{"idResponsableMesure":$scope.idResp},
                    "objectif":{"idObjectif":idObjectif}
                };

                var task = dao.getData(properties.urlMesure+"/"+idObjectif, null, HTTP_METHOD.put,mesure);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.action.listerObjectif();

                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {
                            title: properties.modifierMesureError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };






        }])
;


