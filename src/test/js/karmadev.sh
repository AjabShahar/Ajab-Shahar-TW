#!/bin/bash

echo ""
echo "Starting Karma Server (http://karma-runner.github.io)"
echo "-------------------------------------------------------------------"

../../main/resources/assets/node_modules/karma/bin/karma start ../../test/js/config/karma.dev.conf.js $*
