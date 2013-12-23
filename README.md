# emorg-lab #
EmoRG.eu software for supporting human-computer interaction experiments

## Build and install instructions ##

### Server software ###

#### Database ####
1. Download and install latest PostgreSQL Server (Download site: http://www.postgresql.org/download/),
2. In postgres server create role with name "emorg" and password "emorg" (password can be different, see persistence.xml file),
3. In postgres server create empty database "emorg-prod" with owner "emorg",

#### Building server software ####
1. Download and install jdk1.7+
-Download server source code (placed in java directory)
-Download and install maven 3.0+
-Inside of server source "java" directory run:
> ~/java$ mvn clean package -DskipTests

This will download required libraries and eventually build server software. Inside of "java/netbeans/application/target" directory
there will be *.zip file which contains software (eg netbeans-app-1.0-SNAPSHOT).

Notice that zip file name can be different and is depending on source code version you have build from.

## Development requirements ##
1. Jdk1.6+
2. Maven
3. NetBeansIDE for developing NetBeansRCP code
4. File editor
5. PostgreSQL server with two databases: emorg-prod for running installation and emorg-test for running integration tests
6. Visual C# Express 2010+ (or any other C# approved IDE)