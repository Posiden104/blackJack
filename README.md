# Black Jack

Black Jack is a program that we are working on for CS252 systems programming at Purdue University.


To push to BLUEMIX:
bluemix api https://api.ng.bluemix.net
bluemix login -u <email> -o jvanauke -s dev
cf push Game21 -p Game21.jar -b liberty-for-java
