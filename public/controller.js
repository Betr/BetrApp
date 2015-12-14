
 (function () {

     angular
     .module('betrApp')

     .controller('MainController', function ($scope, PressService, CommunityService, PostService, UserService, $location, LoginService ) {
       var vm = this;
      //  vm.getCommunity = function (){
      //    CommunityService.newCommunity().then(function(){vm.item = items;});
       //
      //  };
      //  vm.getPost = function (item){
      //    PostService.newPost(item);
      //  };
      //  vm.getPress = function (item){
      //    UserService.newPress(item);
      //  };
       vm.addUser = function (item){
           UserService.addUser(item);
           $location.path('/home');
         };
         vm.logUser = function (item){
             LoginService.logUser(item);
             $location.path('/home');
           };
     })
     .controller('AdminController', function ($log, $uibModal, $scope, PressService, CommunityService, PostService, UserService, $location, LoginService ) {
       var vm = this;

       vm.addPost = function (postItem){
           PostService.newPost(postItem);
           $location.path('/admin');
         };

         vm.getPost = function (postItem){
           PostService.newPost(postItem);
         };
         vm.editPost = function (postItem){
           PostService.newPost(postItem);
         };
         vm.deletePost = function (postItem){
           PostService.deletePost(postItem);
         };

         vm.addCommunity = function (item){
           console.log("controller item", item)
             CommunityService.newCommunity(item);
            // $location.path('/communities');
           };
           vm.getCommunity = function (){
             console.log("in admin controller");
             CommunityService.getCommunity().then(function(res){vm.items = res.data;});
           };
           vm.getCommunity();
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

          vm.addPress = function (pressItem){
              PressService.newPress(pressItem);
              // $location.path('/admin');
            };
            vm.getPress = function (pressItem){
              PressService.getPress(pressItem);
            };
            vm.getPress();
            vm.editPress = function (pressItem){
              PressService.editPress(pressItem);
            };
            vm.deletePress = function (pressItem){
              PressService.deletePress(pressItem);
            };

            $scope.animationsEnabled = true;

              $scope.open = function (size) {

                var modalInstance = $uibModal.open({
                  animation: $scope.animationsEnabled,
                  templateUrl: '#/myModalContent.html',
                  controller: 'ModalInstanceCtrl',
                  size: size,
                  resolve: {
                    items: function () {
                      return $scope.items;
                    }
                  }
                });

                modalInstance.result.then(function (selectedItem) {
                  $scope.selected = selectedItem;
                }, function () {
                  $log.info('Modal dismissed at: ' + new Date());
                });
              };

              $scope.toggleAnimation = function () {
                $scope.animationsEnabled = !$scope.animationsEnabled;
              };
            })

        .controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

              $scope.items = items;
              // $scope.selected = {
              //   item: $scope.items[0]
              // };

              $scope.ok = function () {
                $uibModalInstance.close($scope.selected.item);
              };

              $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
              };
        });

// ModalInstanceCtrl
 })();
