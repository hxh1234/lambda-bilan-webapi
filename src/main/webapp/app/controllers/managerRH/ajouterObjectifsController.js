var app = angular.module("lambda.bilan");



app.controller("ajouterObjectifsController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

                $scope.objectifs=[];

                var idCollaborateur;

                $scope.action.ajouterObjectif=function(id){
                  $scope.objectifs=[];
                  idCollaborateur=id;
                };

                $scope.ajouterObjectif=function(){
                    var objectif={
                        collaborateur:{"idUtilisateur":idCollaborateur},
                        nomObjectif:$scope.nomObjectif,
                        descriptifObjectif:$scope.descriptifObjectif,
                        categorie:{nomCategorie:$scope.categorie.nomCategorie,idCategorie:$scope.categorie.idCategorie}
                    };
                    $scope.objectifs.push(objectif);

                };

                $scope.supprimerObjectif=function(objectif){
                    for (var i =0; i < $scope.objectifs.length; i++)
                        if ($scope.objectifs[i] === objectif) {
                            $scope.objectifs.splice(i,1);
                            break;
                        }
                };

                 $scope.valider=function(){
                    var task = dao.getData(properties.urlObjectif, null, HTTP_METHOD.post,$scope.objectifs);
                    //on attent la reponse...
                    task.promise.then(function (result) {
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            //Pas d'erreur
                            $scope.action.listerCollabsSansObj();

                        } else {
                            // il y a eu des erreurs
                            $scope.errors = {

                                title: properties.ajouterObjectifsErrors,
                                messages: utils.getErrors(result),
                                show: true
                            };
                        }
                    });

                 };




        }])
;


