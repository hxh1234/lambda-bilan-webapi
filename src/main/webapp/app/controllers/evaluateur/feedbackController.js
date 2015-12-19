var app = angular.module("lambda.bilan");

app.controller("feedbackController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            $scope.themes=[];
            var task = dao.getData(properties.urlTheme, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.themes = result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recupererThemeError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.qualifications=[];
            var task = dao.getData(properties.urlQualification, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.qualifications = result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recupererQualifError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            var url;
            $scope.action.creerFeedback=function(collaborateur)
            {
                $scope.collaborateur=collaborateur;
                $scope.intervention = {};
                url = properties.urlIntervention + "/" + $scope.projet.idProjet + "/" +collaborateur.idUtilisateur;
                var task = dao.getData(url, null, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.intervention = result.data;
                        if($scope.intervention.notes.length==0){
                            for(var i=0;i<$scope.themes.length;i++){
                                $scope.intervention.notes.push({theme:$scope.themes[i],qualification:{}});
                            }
                        }

                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {
                            title: properties.recupererInterventionError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            }

            $scope.enregitrerFeedback=function(){
                var task = dao.getData(properties.urlFeedback, null, HTTP_METHOD.post,$scope.intervention.notes);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        $scope.errors.show = false;
                        $scope.succes.show=true;
                        $scope.succes.message=result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.enregistrerFeedbackError;
                        $scope.errors.messages = utils.getErrors(result);
                    }
                });

            };

            $scope.validerFeedback=function(){
                var task = dao.getData(properties.urlFeedback+"/"+$scope.intervention.idIntervention,
                    null, HTTP_METHOD.put,$scope.intervention.notes);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        $scope.errors.show = false;
                        $scope.succes.show=true;
                        $scope.succes.message=result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.validerFeedbackError;
                        $scope.errors.messages = utils.getErrors(result);
                    }
                });

            };



        }])
;

