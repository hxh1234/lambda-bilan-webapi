var app = angular.module("lambda.bilan", ["ngCookies","ngTable","isteven-multi-select"]);



app.controller("managerRhAccueilController",
    ['$scope','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            var idManagerRH=11;

            $scope.collabsSansProjet=[];
            var listerCollabsSansProjet = function(){
                //get les collabs sans projet
                var task = dao.getData(properties.urlCollabsSansProjet, null, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.collabsSansProjet = result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {

                            title: properties.listerCollabsSansProjetError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };
            listerCollabsSansProjet();

            $scope.collabsSansObj=[];
            var listerCollabsSansObj=function(){
                //get les collabs sans objectifs
                var task = dao.getData(properties.urlCollabsSansObj, null, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.collabsSansObj = result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {

                            title: properties.listerCollabsSansObjError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };
            listerCollabsSansObj();

            $scope.objectifsRef=[];
            var listerObjectifsRef=function(){
                    //get les objectifs refusés
                    var task = dao.getData(properties.urlObjRefus+"/"+idManagerRH, null, HTTP_METHOD.get);
                    //on attent la reponse...
                    task.promise.then(function (result) {
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            //Pas d'erreur
                            $scope.objectifsRef = result.data;
                        } else {
                            // il y a eu des erreurs
                            $scope.errors = {

                                title: properties.listerObjRefusError,
                                messages: utils.getErrors(result),
                                show: true
                            };
                        }
                    });
            };
            listerObjectifsRef();


            $scope.supprimerObjectif=function(id){
                var task = dao.getData(properties.urlObjectif+"/"+id, null, HTTP_METHOD.delete);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        listerObjectifsRef();
                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {

                            title: properties.supprimerObjectifError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };

            $scope.validerObjectif=function(id){
                var task = dao.getData(properties.urlValiderObjectif+"/"+id, null, HTTP_METHOD.put);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreurs
                        listerObjectifsRef();
                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {

                            title: properties.validerObjectifError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };



            $scope.projetsInput=[];
            var task = dao.getData(properties.urlObjectif, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreurs
                    $scope.projetsInput=result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.validerObjectifError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });


            $scope.affecterProjet=function(){
                var projets = $scope.projetsOutput;
                var task = dao.getData(properties.urlAffecterProjet, null, HTTP_METHOD.post);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreurs
                        listerCollabsSansProjet();
                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {
                            title: properties.affecterProjetError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });


            };

            $scope.action={ajouterObjectif:undefined};







        }])
;

