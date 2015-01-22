if [ -f .bash_profile ]; then
cat as_pid_file | xargs kill -9
else
java -jar platform-1.0-SNAPSHOT.jar server $1 > server.log &
echo $! > as_pid_file
fi