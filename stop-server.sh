#!/bin/sh

pid=`pgrep -f platform-1.0-SNAPSHOT.jar`

if [ "$pid" != "" ]
then
  kill -9 $pid
fi
