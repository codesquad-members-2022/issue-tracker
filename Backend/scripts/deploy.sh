#1/bin/bash

# init variable
PROJECT_NAME="issue-tracker"
APP_DIR="/home/ec2-user/app"
LOG_PATH=$APP_DIR"/log"

# create log dir if not exists
if [ ! -d $LOG_PATH ]
then
  echo "mkdir $LOG_PATH"
  `mkdir $LOG_PATH`
fi

# create log name
TODAY=`date '+%Y-%m-%dT%H:%M:%S'`
LOG_NAME="$PROJECT_NAME-$TODAY"

# grant jar file
echo "sudo chmod +x $APP_DIR/build/libs/*.jar"
`sudo chmod +x $APP_DIR/build/libs/*.jar`

# move to app dir, rename jar
echo "cp $APP_DIR/build/libs/*.jar $APP_DIR/$PROJECT_NAME.jar"
`cp $APP_DIR/build/libs/*.jar $APP_DIR/$PROJECT_NAME.jar`

# find running process PID
echo "sudo jps | grep $PROJECT_NAME.jar | awk '{ print $1 }'"
CURR_PID=`sudo jps | grep $PROJECT_NAME.jar | awk '{ print $1 }'`
echo $CURR_PID

# kill running process if exists
if [ -z "$CURR_PID" ]
then
  echo "no running process"
else
  echo "kill -9 $CURR_PID"
  `kill -9 $CURR_PID`
fi

# run new process
echo "nohup java -jar $APP_DIR/$PROJECT_NAME.jar > $LOG_PATH/$LOG_NAME.log 2>&1 &"
`nohup java -jar $APP_DIR/$PROJECT_NAME.jar > $LOG_PATH/$LOG_NAME.log 2>&1 &`
