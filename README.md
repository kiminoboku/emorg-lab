# emorg-lab #
EmoRG.eu software for supporting human-computer interaction experiments

## Build and install instructions ##

### Server software ###

#### Database ####
1. Download and install latest PostgreSQL Server (Download site: http://www.postgresql.org/download/),
2. In postgres server create role with name "emorg" and password "emorg" (password can be different, see Configuration section),
3. In postgres server create empty database "emorg-prod" with owner "emorg",

#### Glassfish application server ####
1. Download and install jdk1.6+,
2. Download and install glassfish3 (Download site: https://glassfish.java.net/download-archive.html),
3. Download appropriate jdbc driver (Download site: http://jdbc.postgresql.org/download.html) and copy it into glassfish3/lib directory,
4. Start glassfish domain,

#### Building server software ####
-Download server source code (emorg-server)
-Download and install maven 3.0+
-Inside of server source "emorg-parent" directory run:
> ~/emorg-server/emorg-parent$ mvn clean package -DskipTests

This will download required libraries and eventually build server software. Inside of "emorg-ear/target/dist" directory
there will be *.ear file which contains software and *.xml file that contains necessary data for connecting with
postgresql database,

-Install glassfish-resources.xml file running:
> $ asadmin add-resources ~/emorg-server/emorg-ear/target/dist/glassfish-resources.xml

Glassfish will use login and password stored in that file when connecting with postgresql database so if your postgres
login and/or password is different than default you have to change it also in glassfish-resources.xml file,

-Deploy ear file via web or by running for example:
> $ asadmin deploy ~/emorg-server/emorg-ear/target/dist/emorg-ear-1.0.ear

Notice that ear file name can be different and is depending on source code version you have build from.

## Development requirements ##
1. Jdk1.6+
2. Maven
3. File editor
4. PostgreSQL server with two databases: emorg-prod for running installation and emorg-test for running integration tests
5. Glassfish3 installation with configured data source (see glassfish-resources.xml) and installed proper jdbc driver