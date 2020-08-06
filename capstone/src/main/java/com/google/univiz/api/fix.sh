
#!/bin/bash
#
# Formats a Java source file using https://github.com/google/google-java-format
#
# COMMIT OFTEN AND USE AT YOUR OWN RISK.

JAVA_FORMATTER_JAR=https://github.com/google/google-java-format/releases/download/google-java-format-1.7/google-java-format-1.7-all-deps.jar
LOCAL_JAVA_FORMATTER_PATH=/tmp/google-java-format-1.7-all-deps.jar

wget -q -nc -O $LOCAL_JAVA_FORMATTER_PATH $JAVA_FORMATTER_JAR

java -jar $LOCAL_JAVA_FORMATTER_PATH --replace $1 
