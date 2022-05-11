javac -d classes/ -cp lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar src/*.java
java -cp classes/:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore TestLists 2>&1 #| grep -v "at "
