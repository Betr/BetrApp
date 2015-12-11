
 (function () {

     angular
     .module('betrApp')

     .controller('MainController', function ($scope, CommunityService, PostService, UserService, $location, LoginService ) {
       var vm = this;
       vm.getCommunity = function (item){
         CommunityService.newCommunity(item);
         vm.item = items;
       };
       vm.getPost = function (item){
         PostService.newPost(item);
       };
       vm.getPress = function (item){
         UserService.newPress(item);
       };
       vm.addUser = function (item){
           UserService.addUser(item);
           $location.path('/home');
         };
         vm.logUser = function (item){
             LoginService.logUser(item);
             $location.path('/home');
           };
     })
     .controller('AdminController', function (vm, CommunityService, PostService, UserService, $location, LoginService ) {
       var vm = this;

       vm.addPost = function (item){
           PostService.newPost(item);
           $location.path('/admin');
         };

         vm.getPost = function (item){
           PostService.newPost(item);
         };
         vm.editPost = function (item){
           PostService.newPost(item);
         };
         vm.deletePost = function (item){
           PostService.deletePost(item);
         };

         vm.addCommunity = function (item){
           console.log("controller item", item)
             CommunityService.newCommunity(item);
             $location.path('/communities');
           };
           vm.getCommunity = function (item){
             CommunityService.newCommunity(item);
           };
           vm.editCommunity = function (item){
             CommunityService.newCommunity(item);
           };
           vm.deleteCommunity = function (item){
             CommunityService.deleteCommunity(item);
           };
          //  vm.addUser = function (item){
          //      UserService.addUser(item);
          //      $location.path('/home');
          //    };
          //    vm.logUser = function (item){
          //        LoginService.logUser(item);
          //        $location.path('/home');
          //      };

     });


 })();
