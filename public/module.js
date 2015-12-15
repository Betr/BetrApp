
(function () {
  "use strict";

  angular
    .module('betrApp', [
      'ngRoute',
      'underscore',
      'ui.bootstrap'

    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/', {
          templateUrl: 'views/main.html',
          controller: 'MainController as MainCtrl'
        })
        .when('/communities', {
          templateUrl: 'views/communityDonate.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/payment', {
          templateUrl: 'views/popup.html',
          controller: 'ModalInstanceCtrl'
        })
        .when('/home', {
          templateUrl: 'views/loggedinHome.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/press', {
          templateUrl: 'views/press.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/404', {
          template: "<h1> SORRY NO BUENO </h1>"
        })
        .when('/admin', {
          templateUrl: 'adminViews/admin.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/editcommunities', {
          templateUrl: 'adminViews/editCommunities.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/editpress', {
          templateUrl: 'adminViews/editPress.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/newpost', {
          templateUrl: 'adminViews/postAndUpdate.html',
          controller: 'AdminController as AdminCtrl'
        })
        .when('/login', {
          templateUrl: 'views/login.html',
          controller: 'MainController as MainCtrl'
        })
        .when('/register', {
          templateUrl: 'views/register.html',
          controller: 'MainController as MainCtrl'
        })
        .otherwise("/404")
    });

  angular
    .module('underscore', [])
    .factory('_', function ($window) {
      return $window._;
    });


})();
