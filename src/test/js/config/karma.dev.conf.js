'use strict';

var buildConfig = require('./karma.conf.js');
module.exports = function(config) {
        buildConfig(config);
        config.singleRun = false;
        config.reporters = ['dots', 'junit'];
};
