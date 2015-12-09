
(function () {
  "use strict";

  angular
    .module('betrApp', [
      'ngRoute',
      'underscore'

    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/', {
          templateUrl: 'views/main.html',
          controller: 'MainController as MainCtrl'
        })
        .when('/communities', {
          templateUrl: 'views/communityDonate.html',
          controller: 'MainController as MainCtrl'
        })
        .when('/home', {
          templateUrl: 'views/loggedinHome.html',
          controller: 'MainController as MainCtrl'
        })
        .when('/press', {
          templateUrl: 'views/press.html',
          controller: 'MainController as MainCtrl'
        })
        .when('/404', {
          template: "<h1> SORRY NO BUENO </h1>"
        })
        .when('/admin', {
          templateUrl: 'adminViews/admin.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/editcommunities', {
          templateUrl: 'adminViews/editcommunities.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/editpress', {
          templateUrl: 'adminViews/editpress.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/newpost', {
          templateUrl: 'adminViews/postAndUpdate.html',
          controller: 'AdminController as AdminCtrl'
        })
        .otherwise("/404")
    });

  angular
    .module('underscore', [])
    .factory('_', function ($window) {
      return $window._;
    });


})();
