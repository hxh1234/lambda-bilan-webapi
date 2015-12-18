var app = angular.module("lambda.bilan");



app.controller("modifierObjectifController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            var idCollaborateur;
            var idObjectif;

            $scope.action.modifierObjectif=function(obj){
                $scope.nomObjectif=obj.nomObjectif;
                $scope.idCategorie=obj.categorie.idCategorie;
                $scope.descriptifObjectif=obj.descriptifObjectif;
                idCollaborateur=obj.collaborateur.idUtilisateur;
                idObjectif=obj.idObjectif;

            };

            $scope.modifier=function(){
                var objectif={
                    idObjectif:idObjectif,
                    collaborateur:{idUtilisateur:idCollaborateur},
                    nomObjectif:$scope.nomObjectif,
                    descriptifObjectif:$scope.descriptifObjectif,
                    categorie:{idCategorie:$scope.idCategorie}
                };
                console.log("////  "+angular.toJson(objectif));
                var task = dao.getData(properties.urlObjectif+"/"+idObjectif, null, HTTP_METHOD.put,objectif);
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
                            title: properties.modifierObjectifError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };





        }])
;


