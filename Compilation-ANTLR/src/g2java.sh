#!/bin/sh

# g2java.sh: converts ANTLR .g files into .java files
# exemple: ./g2java.sh TP1.g

# Tests if the user has given an argument
if [ $# -lt 1 ]
then
  echo "./g2java.sh - converts ANTLR .g files into .java files"
  echo "syntax: ./g2java.sh <file.g>"
  echo "error: no grammar file specified"
  exit 1
fi

# Tests if the ANTLR file is readable
ANTLR_JAR=../antlr-3.5.2-complete.jar
if [ ! -r $ANTLR_JAR ]
then
  echo "error: could not read ANTLR Jar file: $ANTLR_JAR"
  echo "check if file exists and is readable"
  exit 2
fi

# Tests if the grammar file is readable
if [ ! -r $1 ]
then
  echo "error: could not read file: $1"
  exit 2
fi

export CLASSPATH=../antlr-3.5.2-complete.jar:.:$CLASSPATH
java org.antlr.Tool $1

