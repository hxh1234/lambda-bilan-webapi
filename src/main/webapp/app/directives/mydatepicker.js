angular.module("lambda.bilan")
    .directive("mydatepicker", function(){
        return {
            restrict: "E",
            scope:{
                ngModel: "=",
                dateOptions: "=",
                opened: "="
            },
            link: function($scope, element, attrs) {
                $scope.open = function(event){
                    event.preventDefault();
                    event.stopPropagation();
                    $scope.opened = true;
                };

                $scope.clear = function () {
                    $scope.ngModel = null;
                };
            },
            templateUrl: '../../directives/datepicker.html'
        }
})