require.config({
  paths: {
    braintree: "https://js.braintreegateway.com/v2/braintree"
  }
});

require(["braintree"], function (braintree) {
  braintree.setup("CLIENT-TOKEN-FROM-SERVER", "INTEGRATION_TYPE", options);
});
