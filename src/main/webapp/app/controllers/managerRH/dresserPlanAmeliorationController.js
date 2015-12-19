var app = angular.module("lambda.bilan");

app.controller("dresserPlanAmeliorationController",
    ['$scope','$http','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,$http,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ){

            $scope.planFormations=[];
            $scope.actions=[];
            $scope.objectifsInput=[];

            var idCollaborateur;
            var init=false;
            $scope.action.dresserPlanAmelioration=function(id){
                idCollaborateur=id;
                $scope.planFormations=[];
                $scope.actions=[];
                $scope.objectifsInput=[];
                //chargement des objectifs du collab
                var task = dao.getData("/collaborateurs/"+idCollaborateur+"/objectifs", null, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreurs
                        console.log("===>"+angular.toJson(result.data));
                        $scope.objectifsInput=result.data;
                        for(var i=0;i<$scope.objectifsInput.length;i++){
                            $scope.objectifsInput[i].name=$scope.objectifsInput[i].nomObjectif;
                            $scope.objectifsInput[i].maker="  : "+$scope.objectifsInput[i].descriptifObjectif;
                        }
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.recuperationObjectifError;
                        $scope.errors.messages = utils.getErrors(result);
                    }
                });
            };

            $scope.ajouterPlanFormation=function(){
                var planFormation={
                    dureePlanFormation:$scope.duree,
                    moyensPlanFormation: $scope.moyens,
                    objectifs:$scope.objectifsOutput
                };
                $scope.planFormations.push(planFormation);
            };

            $scope.supprimerPlanFormation=function(planFormation){
                for (var i =0; i < $scope.planFormations.length; i++)
                    if ($scope.planFormations[i] === planFormation) {
                        $scope.planFormations.splice(i,1);
                        break;
                    }
            };

            $scope.ajouterAction=function(){
                var action={
                    messageAction: $scope.message,
                    collaborateur: {"idUtilisateur": idCollaborateur}
                };
                $scope.actions.push(action);
            };

            $scope.supprimerAction=function(action){
                for (var i =0; i < $scope.actions.length; i++)
                    if ($scope.actions[i] ===action) {
                        $scope.actions.splice(i,1);
                        break;
                    }
            };

            $scope.valider=function(){
                var planAmelioration={
                    planFormations:$scope.planFormations,
                    actions:$scope.actions
                };
                console.log(angular.toJson(planAmelioration));

                var task = dao.getData(properties.urlPlanAmelioration, null, HTTP_METHOD.post,planAmelioration);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.errors.show = false;
                        $scope.succes.show=true;
                        $scope.succes.message=result.data;

                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.dresserPlanAmeliorationError;
                        $scope.errors.messages = utils.getErrors(result);
                    }
                });
            };
}])
;

