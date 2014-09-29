cat as_pid_file | xargs kill -9
nohup java -jar platform-1.0-SNAPSHOT.jar server $1 > server.log &
echo $! > as_pid_file