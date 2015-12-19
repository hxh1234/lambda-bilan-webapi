var app = angular.module("lambda.bilan", ["ngCookies"]);

app.controller("bipController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            //get idCollaborateur
         /*   var idCollaborateur=$cookies.getObject("idCollab");
            $cookies.putObject("idCollab",undefined);*/
            $scope.user={nomUtilisateur :"Jaouad",prenomUtilisateur:"Elgharrasse"};
            $scope.errors={show:false,messages:[]};
            $scope.succes={show:false,message:''};
            var collaborateur = $cookies.getObject("collaborateur");
            $cookies.putObject("collaborateur",null);
            var idCollaborateur = collaborateur.idUtilisateur;
                $scope.objectifs=[];
                var listerObjectif= function () {
                    var task = dao.getData(properties.urlCollaborateur+"/"+idCollaborateur+"/objectifs", null, HTTP_METHOD.get);
                    //on attent la reponse...
                    task.promise.then(function (result) {
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            //Pas d'erreur
                            $scope.objectifs=result.data;
                            console.log(angular.toJson(result.data));

                        } else {
                            // il y a eu des erreurs
                            $scope.succes.show=false;
                            $scope.errors.show = true;
                            $scope.errors.title = properties.listerObjectifError;
                            $scope.errors.messages = utils.getErrors(result);
                        }
                    });
                };

                listerObjectif();

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
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.recuperationsCategoriesError;
                        $scope.errors.messages = utils.getErrors(result);
                    }
                });

                $scope.responsableMesures=[];
                var task = dao.getData(properties.urlRespMesure, null, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreurs
                        $scope.responsableMesures=result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.recuperationsRespError;
                        $scope.errors.messages = utils.getErrors(result);
                    }
                });

                $scope.ajouterObjectif=function(){
                    var objectif=[{
                        collaborateur:{"idUtilisateur":idCollaborateur},
                        nomObjectif:$scope.nomObjectif,
                        descriptifObjectif:$scope.descriptifObjectif,
                        categorie:{idCategorie:$scope.categorie.idCategorie}
                    }];
                    var task = dao.getData(properties.urlObjectif, null, HTTP_METHOD.post,objectif);
                    //on attent la reponse...
                    task.promise.then(function (result) {
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            //Pas d'erreur
                            $scope.errors.show = false;
                            $scope.succes.show=true;
                            $scope.succes.message=result.data;
                           listerObjectif();

                        } else {
                            // il y a eu des erreurs
                            $scope.succes.show=false;
                            $scope.errors.show = true;
                            $scope.errors.title = properties.ajouterObjectifError;
                            $scope.errors.messages = utils.getErrors(result);
                        }
                    });
                };

                var idObjectif;
                $scope.setIdObjectif=function(id){
                    idObjectif=id;
                };

                $scope.ajouterMesure=function () {
                  var mesure= [{
                      "commentMesurer":$scope.comment,
                      "poidsMesure":$scope.poids,
                      "resultatMesure":$scope.resultat,
                      "modeAccesMesure":$scope.mode,
                      "responsableMesure":{"idResponsableMesure":$scope.idResp},
                      "objectif":{"idObjectif":idObjectif}
                  }];
                    console.log(angular.toJson(mesure));

                    var task = dao.getData(properties.urlMesure, null, HTTP_METHOD.post,mesure);
                    //on attent la reponse...
                    task.promise.then(function (result) {
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            //Pas d'erreur
                            $scope.errors.show = false;
                            $scope.succes.show=true;
                            $scope.succes.message=result.data;
                            listerObjectif();

                        } else {
                            // il y a eu des erreurs
                            $scope.succes.show=false;
                            $scope.errors.show = true;
                            $scope.errors.title = properties.ajouterMesureError;
                            $scope.errors.messages = utils.getErrors(result);
                        }
                    });


                };

                $scope.supprimerObjectif=function(id){
                    var task = dao.getData(properties.urlObjectif+"/"+id, null, HTTP_METHOD.delete);
                    //on attent la reponse...
                    task.promise.then(function (result) {
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            $scope.errors.show = false;
                            $scope.succes.show=true;
                            $scope.succes.message=result.data;
                            listerObjectif();
                        } else {
                            // il y a eu des erreurs
                            // il y a eu des erreurs
                            $scope.succes.show=false;
                            $scope.errors.show = true;
                            $scope.errors.title = properties.supprimerObjectifError;
                            $scope.errors.messages = utils.getErrors(result);
                        }
                    });

                };

                $scope.supprimerMesure=function(id){
                    console.log("init suppr mesure "+id);
                    var task = dao.getData(properties.urlMesure+"/"+id, null, HTTP_METHOD.delete);
                    //on attent la reponse...
                    task.promise.then(function (result) {
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            $scope.errors.show = false;
                            $scope.succes.show=true;
                            $scope.succes.message=result.data;
                            listerObjectif();
                        } else {
                            // il y a eu des erreurs
                            $scope.succes.show=false;
                            $scope.errors.show = true;
                            $scope.errors.title = properties.supprimerMesureError;
                            $scope.errors.messages = utils.getErrors(result);
                        }
                    });

                };

                $scope.action={
                    modifierObjectif:undefined,
                    modifierMesure:undefined,
                    listerObjectif:listerObjectif
                };









        }])
;

