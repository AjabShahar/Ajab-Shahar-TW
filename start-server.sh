pgrep -f platform-1.0-SNAPSHOT.jar | xargs kill -9;
java -jar target/platform-1.0-SNAPSHOT.jar server $1 > server.log &
