/**
 * Created by ST on 05/05/2014.
 */
var app=angular.module("lambda.bilan");
    app.factory('security', ['$cookies','AUTH','properties',
        function ($cookies,AUTH,properties) {

            //récuperer l'utilisateur en session
            function getCurrentUser() {
                return $cookies.getObject("user");
            }

            //enregistrer l'utilisateur en session
            function  setCurrentUser(user) {
                $cookies.putObject("user",user);
            }
            //valider est ce que l'utilisateur  en paramatre possede l'authorisation
            //retourne :
            // AUTH.authorized si il est autorisé
            //AUTH.notAuthorized si il n'est pas autorisé
            //AUTH.notAuthenticated si il n'est pas authentifié
            function authorize(user,allowedRoles) {
                var result = AUTH.authorized;
                var hasPermission = true;

                if ( user === undefined) {
                    result = AUTH.notAuthenticated;
                } else if ( user !== undefined && allowedRoles === "*") {
                    // Login is required but no specific roles are specified.
                    result = AUTH.authorized;
                } else {
                    if(requiredPermissions.indexOf(user.role) == -1)
                        hasPermission=false;
                    result = hasPermission ? AUTH.authorized : AUTH.notAuthorized;
                }
                return result;
            };

            //retourne l'utilisateur en session si il est autorisé sinn redirigé
            function checkSecurity (allowedRoles){
              var result = authorize(getCurrentUser(),allowedRoles);
                switch(result){
                    case  AUTH.notAuthenticated :
                          utils.redirectTo(properties.urlLogin);
                          break;

                    case AUTH.notAuthorized:
                        utils.redirectTo(properties.urlForbidden);
                         break;

                    case AUTH.authorized:
                         return getCurrentUser;
                         break;

                    default:
                         break;
                }


            };



            // --------------------- instance du service [security]
            return {
                checkSecurity:checkSecurity,
                getCurrentUser:getCurrentUser,
                setCurrentUser:setCurrentUser
            }
        }]);
