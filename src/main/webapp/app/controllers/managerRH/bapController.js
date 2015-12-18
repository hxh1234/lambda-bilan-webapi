var app = angular.module("lambda.bilan", ["ngCookies"]);



app.controller("bapController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            var idCollaborateur = 3;
            $scope.objectifs = [];
            $scope.resultatFinal=0;
            var listerObjectif = function () {
                var task = dao.getData(properties.urlCollaborateur + "/" + idCollaborateur + "/objectifs", null, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.objectifs = result.data;

                        for(var i=0;i<$scope.objectifs.length;i++){
                            for(var j=0;j<$scope.objectifs[i].mesures.length;j++){
                                $scope.resultatFinal+=$scope.objectifs[i].mesures[j].poidsMesure *
                                    $scope.objectifs[i].mesures[j].resultatMesure;
                            }
                        }

                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {
                            title: properties.listerObjectifError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };

            listerObjectif();

            $scope.categories = [];
            var task = dao.getData(properties.urlCategorie, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreurs
                    $scope.categories = result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recuperationsCategoriesErrors,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.responsableMesures = [];
            var task = dao.getData(properties.urlRespMesure, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreurs
                    $scope.responsableMesures = result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recuperationsRespError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });




            $scope.supprimerMesure = function (objectif,mesure) {

                for (var i = 0; i < $scope.objectifs.length; i++){
                    if ($scope.objectifs[i] === objectif ) {
                        for (var j = 0; j < $scope.objectifs[i].mesures.length; j++){
                            if($scope.objectifs[i].mesures[j]===mesure){
                            $scope.objectifs[i].mesures.splice(j, 1);
                            break;
                            }
                        }
                    }
                }

                $scope.resultatFinal-=mesure.resultatMesure*mesure.poidsMesure;
            };


            var objectif;
            $scope.setObjectif=function(_objectif){
              objectif=_objectif;
            };
            $scope.ajouterMesure=function () {
                var mesure = {
                    "commentMesurer": $scope.comment,
                    "poidsMesure": $scope.poids,
                    "resultatMesure": $scope.resultat,
                    "modeAccesMesure": $scope.mode,
                    "responsableMesure": {"idResponsableMesure": $scope.idResp},
                    "objectif": {"idObjectif": objectif.idObjectif}
                };

                for (var i = 0; i < $scope.objectifs.length; i++)
                    if ($scope.objectifs[i] === objectif) {
                        $scope.objectifs[i].mesures.push(mesure);
                        break;
                    }

                $scope.resultatFinal+=mesure.resultatMesure*mesure.poidsMesure;


            };


            var mesure;
            $scope.setMesure=function(_objectif,_mesure){
                mesure=_mesure;
                $scope.comment=mesure.commentMesurer;
                $scope.poids=mesure.poidsMesure;
                $scope.resultat=mesure.resultatMesure;
                $scope.mode=mesure.modeAccesMesure;
                $scope.idResp= mesure.responsableMesure.idResponsableMesure;
                objectif=_objectif;
            };

            $scope.modifierMesure=function(){

                $scope.resultatFinal-=mesure.resultatMesure*mesure.poidsMesure;
               // console.log("/////  "+objectif.mesures.length);
                var _mesure = {
                    "idMesure":mesure.idMesure,
                    "commentMesurer": $scope.comment,
                    "poidsMesure": $scope.poids,
                    "resultatMesure": $scope.resultat,
                    "modeAccesMesure": $scope.mode,
                    "responsableMesure": {"idResponsableMesure": $scope.idResp},
                    "objectif": {"idObjectif": objectif.idObjectif}
                };
                for (var i = 0; i < $scope.objectifs.length; i++){
                    if ($scope.objectifs[i] === objectif ) {
                        for (var j = 0; j < $scope.objectifs[i].mesures.length; j++){
                            if($scope.objectifs[i].mesures[j]===mesure){
                                $scope.objectifs[i].mesures[j]=_mesure;
                                mesure=_mesure;
                                break;
                            }
                        }
                    }
                }
                $scope.resultatFinal+=mesure.resultatMesure*mesure.poidsMesure;


            };
//---------------------------------------------------Next Year!---------------------------------------
            $scope.nextObjectifs=[];

            $scope.categories=[];
            var task = dao.getData(properties.urlCategorie, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreurs
                    $scope.categories=result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recuperationsCategoriesErrors,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.ajouterObjectif=function(){
                console.log("=======");
                var objectif={
                    collaborateur:{"idUtilisateur":idCollaborateur},
                    nomObjectif:$scope.nomObjectif,
                    descriptifObjectif:$scope.descriptifObjectif,
                    categorie:{nomCategorie:$scope.categorie.nomCategorie,idCategorie:$scope.categorie.idCategorie}
                };
                $scope.nextObjectifs.push(objectif);

            };

            $scope.supprimerObjectif=function(objectif){
                for (var i =0; i < $scope.nextObjectifs.length; i++)
                    if ($scope.nextObjectifs[i] === objectif) {
                        $scope.nextObjectifs.splice(i,1);
                        break;
                    }
            };


            $scope.valider=function(){

                console.log("iiii");
                var bap={mesures:[],objectifs:[]};
                for (var i = 0; i < $scope.objectifs.length; i++){
                    for (var j = 0; j < $scope.objectifs[i].mesures.length; j++) {
                        bap.mesures.push($scope.objectifs[i].mesures[j]);
                    }
                }
                for (var i = 0; i < $scope.nextObjectifs.length; i++){
                    bap.objectifs.push($scope.nextObjectifs[i]);
                }

                console.log("3333333"+angular.toJson(bap));
                var task = dao.getData(properties.urlEvaluationObjectif, null, HTTP_METHOD.post,bap);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreurs
                      //  $scope.categories=result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {
                            title: properties.validerBAPError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };





        }])
;

