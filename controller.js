
(function () {

    angular
    .module('betrApp')

    .controller('MainController', function ($scope) {
      var vm = this;
      $scope.getCommunity = function (theItem){
        UserService.newItem(theItem);
      };
      $scope.getPost = function (theItem){
        UserService.newItem(theItem);
      };
      $scope.getPress = function (theItem){
        UserService.newItem(theItem);
      };
    })
    .controller('AdminController', function ($scope) {
      $scope.addPost = function (theItem){
          AdminService.newItem(theItem);
          $location.path('/shop');
        };
        $scope.getPost = function (theItem){
          AdminService.newItem(theItem);
        };
        $scope.editPost = function (theItem){
          AdminService.newItem(theItem);
        };
        $scope.deletePost = function (theItem){
          AdminService.deleteItem(theItem);
        };


        $scope.addCommunity = function (theItem){
            AdminService.newItem(theItem);
            $location.path('/shop');
          };
          $scope.getCommunity = function (theItem){
            AdminService.newItem(theItem);
          };
          $scope.editCommunity = function (theItem){
            AdminService.newItem(theItem);
          };
          $scope.deleteCommunity = function (theItem){
            AdminService.deleteItem(theItem);
          };

    })


})();
