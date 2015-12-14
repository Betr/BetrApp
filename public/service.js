

(function () {
  "use strict";
  angular
    .module('betrApp')
    .factory('CommunityService', function ($http) {
      var url = '/community';
      var getUrl = '/communities';
      var addCommunity = function (newCommunity) {
          console.log(newCommunity);
            $http.post(url, newCommunity).then(function (res) {
              console.log(newCommunity);
            });
          };

          var getCommunity = function () {
            console.log('in community service');
            return $http.get(getUrl);
          };
          var editCommunity = function () {
            return $http.put(url + id, data);
          };
          var deleteCommunity = function (item) {
             $http.delete(url + "/" + item._id);
          };

          return {
            newCommunity: addCommunity,
            getCommunity: getCommunity,
            editCommunity: editCommunity,
            deleteCommunity: deleteCommunity,
          };
        })

        .factory('PostService', function ($http) {
          var url = '/posts';
          var addPost = function (newPost) {
                $http.post(url, newPost).then(function (res) {
                  console.log(newPost);
                });
              };

              var getPost = function () {
                return $http.get(url);
              };
              var editPost = function () {
                return $http.put(url + id, data);
              };
              var deletePost = function (item) {
                 $http.delete(url + "/" + item._id);
              };

              return {
                newPost: addPost,
                getPost: getPost,
                editPost: editPost,
                deletePost: deletePost,
                getPost: getPost
              };
            })
            .factory('PressService', function ($http) {
              var url = '/press';
              var addPress = function (newPress) {
                    $http.post(url, newPress).then(function (res) {
                      console.log(newPress);
                    });
                  };

                  var getPress = function () {
                    return $http.get(url);
                  };
                  var editPress = function () {
                    return $http.put(url + id, data);
                  };
                  var deletePress = function (item) {
                     $http.delete(url + "/" + item._id);
                  };

                  return {
                    newPress: addPress,
                    getPress: getPress,
                    editPress: editPress,
                    deletePress: deletePress,
                    getPress: getPress
                  };
                })

    .factory('UserService', function ($http) {
      var url = '/register/';
      var addUser = function (addUser) {
            return $http.post(url, addUser).then(function (res) {
              console.log(addUser);
              console.log(res);
            });
          };

      return {
        addUser: addUser,

    };
  })
  .factory('LoginService', function ($http) {
    var url = '/login';
    var logUser = function (logUser) {
          $http.post(url, logUser).then(function (res) {
            console.log(logUser);
          });
        };

    return {
      logUser: logUser,

  };
});

  })();
