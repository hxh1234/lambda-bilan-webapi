var app = angular.module("lambda.bilan");

    app.constant('HTTP_METHOD',{
        get:'method-get',
        post:'method-post',
        delete:'method-delete',
        put:'method-put'
    });


    app.constant('AUTH',{
        notAuthenticated:'auth-not-authenticated',
        notAuthorized:'auth-not-authorized',
        authorized:'auth-authorized'
    });