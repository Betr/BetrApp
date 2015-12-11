
 (function () {

     angular
     .module('betrApp')

     .controller('MainController', function ($scope, CommunityService, PostService, UserService, $location, LoginService ) {
       var vm = this;
       $scope.getCommunity = function (item){
         CommunityService.newCommunity(item);
       };
       $scope.getPost = function (item){
         PostService.newPost(item);
       };
       $scope.getPress = function (item){
         UserService.newPress(item);
       };
       $scope.addUser = function (item){
           UserService.addUser(item);
           $location.path('/home');
         };
         $scope.logUser = function (item){
             LoginService.logUser(item);
             $location.path('/home');
           };
     })
     .controller('AdminController', function ($scope, CommunityService, PostService, UserService, $location, LoginService ) {
       var vm = this;

       $scope.addPost = function (item){
           PostService.newPost(item);
           $location.path('/admin');
         };

         $scope.getPost = function (item){
           PostService.newPost(item);
         };
         $scope.editPost = function (item){
           PostService.newPost(item);
         };
         $scope.deletePost = function (item){
           PostService.deletePost(item);
         };


         $scope.addCommunity = function (item){
           console.log("controller item", item)
             CommunityService.newCommunity(item);
             $location.path('/communities');
           };
           $scope.getCommunity = function (item){
             CommunityService.newCommunity(item);
           };
           $scope.editCommunity = function (item){
             CommunityService.newCommunity(item);
           };
           $scope.deleteCommunity = function (item){
             CommunityService.deleteCommunity(item);
           };

     });


 })();
