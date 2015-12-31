
 (function () {

     angular
     .module('betrApp')

     .controller('MainController', function ($scope, $log, $uibModal,PressService, CommunityService, PostService, UserService, $location, LoginService ) {
       var vm = this;

       vm.open2 = function (size) {
         console.log("SOMETHING");
         var modalInstance2 = $uibModal.open({
           animation: $scope.animationsEnabled,
           templateUrl: 'views/myModalLogin.html',
           controller: 'ModalInstanceCtrl',
           size: size,
           resolve: {
             items: function () {
               return $scope.items;
             }
           }
         });

         modalInstance2.result.then(function (selectedItem) {
           $scope.selected = selectedItem;
         }, function () {
           $log.info('Modal dismissed at: ' + new Date());
         });
       };

       $scope.toggleAnimation = function () {
         $scope.animationsEnabled = !$scope.animationsEnabled;
       };
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
//PROCESSING payment



       vm.addUser = function (item){
           UserService.addUser(item).success(function() {
             UserService.isUser().success(function(user) {
               if (user.isAdmin) {
                 vm.isUser = true;
               }
             });
           });
         };
        //  vm.logUser = function (item){
        //      LoginService.logUser(item);
        //    };
           vm.logOut = function(item){
             LoginService.logOut().then(function(){
               $location.path('/login');
             });
             };

     })

     .controller('AdminController', function ($log, $uibModal, $scope, PressService, CommunityService, PostService, UserService, $location, LoginService ) {
       var vm = this;

       vm.addPost = function (item){
           PostService.newPost(item).then(function(){vm.getPost();});
          //  $location.path('/admin');
         };

         vm.getPost = function (){
           console.log("posty contrl")
           PostService.getPost().then(function(res){vm.posts = res.data;
             console.log("post res", vm.post);
           })
         };
         vm.getPost();
         vm.editPost = function (item){
           PostService.editPost(item);
         };
         vm.deletePost = function (item){
            console.log("DELETE",item);
           PostService.deletePost(item);
         };

         vm.addCommunity = function (commI){
           console.log("controller item", commI);
             CommunityService.newCommunity(commI).then(function(){vm.getCommunity();});
            // $location.path('/communities');
           };

           vm.getCommunity = function (){
             console.log("in admin controller");
             CommunityService.getCommunity().then(function(res){vm.commIs = res.data;
             vm.numCommunities = vm.commIs.length;
              console.log(vm.numCommunities);});
           };
           //if($location.url() === '/communities' || $location.url() === "/editCommunities")
           vm.getCommunity();

           vm.editCommunity = function (commI){
             console.log("edit working", commI);
            // CommunityService.newCommunity(commI);
           };

           vm.deleteCommunity = function (commI){
             console.log("DELETE",commI);
             CommunityService.deleteCommunity(commI);
           };
          //  vm.addUser = function (item){
          //      UserService.addUser(item);
          //      $location.path('/home');
          //    };
          //    vm.logUser = function (item){
          //        LoginService.logUser(item);
          //        $location.path('/home');
          //      };

          vm.addPress = function (item){
              PressService.newPress(item).then(function(){vm.getPress();});
            };

            if($location.path() === "/editpress") {
              PressService.getPress().success(function(data) {
                vm.items = data;
              });
            }
            if($location.path() === "/press") {
              PressService.getPress().then(function(){vm.getPress();});
          }

            vm.editPress = function (item){
              PressService.editPress(item);
            };
            vm.getPress = function (){
              PressService.getPress().then(function(res){
                vm.items = res.data;
              });
            };
            vm.getPress();

            vm.deletePress = function (item){
              console.log("DELETE",item);
              PressService.deletePress(item);
            };

            $scope.animationsEnabled = true;

              $scope.open = function (size) {

                var modalInstance = $uibModal.open({
                  animation: $scope.animationsEnabled,
                  templateUrl: 'views/myModalContent.html',
                  controller: 'ModalInstanceCtrl',
                  size: size,
                  resolve: {
                    item: function () {
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

              $scope.close = function($uibmodal){
                  $uibmodal.close();
              };


              $scope.toggleAnimation = function () {
                $scope.animationsEnabled = !$scope.animationsEnabled;
              };
            // })
          })

        .controller('ModalInstanceCtrl', function ($scope, PaymentService, $location, LoginService) {


          $scope.postPayment = function (item) {
              console.log("MODAL CLICKAGE",item);
              PaymentService.addPayment(item);
              console.log(item.amount);
              var amt = Math.round(item.amount);
              $scope.total += amt;
              console.log($scope.total);
            };

            $scope.total = 0;


            $scope.logUser = function (item){
                LoginService.logUser(item);
                console.log("im login controller")
                // $location.path('/home');
              };
              // $scope.items = items;
              // $scope.selected = {
              //   item: $scope.items
              // };

              // $scope.ok = function () {
              //   $uibModalInstance.close($scope.selected.item);
              // };
              //
              // $scope.cancel = function () {
              //   $uibModalInstance.dismiss('cancel');
              // };
        });

 })();
