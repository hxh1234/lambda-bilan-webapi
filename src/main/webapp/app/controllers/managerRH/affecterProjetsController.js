var app = angular.module("lambda.bilan");

app.controller("affecterProjetsController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {


            $scope.projetsInput=[];
            var task = dao.getData(properties.urlProjet, null, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreurs
                    console.log("===>"+angular.toJson(result.data));
                    $scope.projetsInput=result.data;
                    for(var i=0;i<$scope.projetsInput.length;i++){
                        $scope.projetsInput[i].name=$scope.projetsInput[i].nomProjet;
                        $scope.projetsInput[i].maker="  : "+$scope.projetsInput[i].nomEvaluateur;
                    }
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recuperationsProjetsErrors,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.action.affecterProjet=function(id){
                $scope.idCollaborateur=id;//input caché dans html
            };

            $scope.affecterProjet=function(isLister){
                var projets = [];
                var item;
                for(var i=0;i<$scope.projetsOutput.length;i++){
                    item={
                        collaborateur : {idUtilisateur:$scope.idCollaborateur},
                        projet : {idProjet:$scope.projetsOutput[i].idProjet}
                    }
                    projets.push(item);
                }
                console.log(angular.toJson(projets));

                var task = dao.getData(properties.urlAffecterProjet, null, HTTP_METHOD.post,projets);
                     //on attent la reponse...
                     task.promise.then(function (result) {
                     // fin d'attente
                     // erreur ?
                     if (result.err == 0) {
                         //Pas d'erreurs
                         $scope.errors.show = false;
                         $scope.succes.show=true;
                         $scope.succes.message=result.data;
                         if(isLister==true)
                         $scope.action.listerCollabSansProjet();
                     } else {
                     // il y a eu des erreurs
                         $scope.succes.show=false;
                         $scope.errors.show = true;
                         $scope.errors.title = properties.affecterProjetError;
                         $scope.errors.messages = utils.getErrors(result);
                     }
                 });
            };
        }])
;


