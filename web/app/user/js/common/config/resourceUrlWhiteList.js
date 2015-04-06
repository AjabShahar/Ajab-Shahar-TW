var resourceUrlWhiteList = function($sceDelegateProvider) {
$sceDelegateProvider.resourceUrlWhitelist([
  // Allow same origin resource loads.
  'self',
  // Allow loading from our assets domain.  Notice the difference between * and **.
  'https%3A%2F%2Fwww.youtube.com%2**',
  'https://www.youtube.com/**',
]);
};
