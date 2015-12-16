/**
 * Created by ST on 05/05/2014.
 */
angular.module("lambda.bilan")
  .factory('dao', ['$http', '$q', 'properties', 'utils','HTTP_METHOD',
    function ($http, $q, properties, utils,HTTP_METHOD) {

      // log

      // liste de données
      function getData(urlAction,basicHeader,http_method,info) {

        // opération asynchrone
        var task = $q.defer();
        // la réponse
        var réponse;
        // les requêtes http doivent être toutes authentifiées
      //  var headers = $http.defaults.headers.common;
       // headers.Authorization = basicHeader;
        // on fait la requête HTTP
        var promise;
          //on construit le URL du service :
        var url = properties.urlServer+""+urlAction;
          console.log("init dao "+url);
        switch(http_method){

            case HTTP_METHOD.get:
                console.log("init get");
               promise = $http.get(url, {timeout: properties.timeout});
                break;

            case HTTP_METHOD.post:
                promise =  $http.post(url, info, {timeout: properties.timeout});
                 break;

            case HTTP_METHOD.delete:
                promise = $http.delete(url, {timeout: properties.timeout});
                  break;

            case HTTP_METHOD.put:
                promise = $http.put(url, info, {timeout: properties.timeout});
                  break;

            default:break;
        }

        promise.then(success, failure);
        // on retourne la tâche elle-même afin de pouvoir extraire les données une fois la reponse http obtenue
        return task;

        // success
        function success(response) {
          // response.data={status:0, data:[med1, med2, ...]} ou {status:x, data=[msg1, msg2, ...]
         // utils.debug("[dao] getData[" + urlAction + "] success réponse", response);
            console.log("init response "+angular.toJson(response));
          // réponse
          var payLoad = response.data;
          réponse = payLoad.status == 0 ? {err: 0, data: payLoad.data} : {err: 1, messages: payLoad.data};
          // on rend la réponse
          task.resolve(réponse);
        }

        // failure
        function failure(response) {
          //utils.debug("[dao] getData[" + urlAction + "] error réponse", response);
          // on analyse le status
            //console.log(angular.toJson(response));
          var status = response.status;
          var error;
          switch (status) {
            case 401 :
              // unauthorized
              error = 2;
              break;
            case 403:
              // forbidden
              error = 3;
              break;
            case 404:
              // not found
              error = 6;
              break;
            case 0:
              // erreur locale
              error = 4;
              break;
            default:
              // autre chose
              error = 5;
          }
          // on rend la réponse
          task.resolve({err: error, messages: [response.statusText]});
        }
      }

      // --------------------- instance du service [dao]
      return {
        getData: getData
      }
    }]);
