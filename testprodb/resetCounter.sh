




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/ASTEVIR12GSVBKRFWCVH2Q
ART_CREATE_PATH=/tmp/applifire/db/ASTEVIR12GSVBKRFWCVH2Q/art/create
AST_CREATE_PATH=/tmp/applifire/db/ASTEVIR12GSVBKRFWCVH2Q/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=root
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=pro1
USER=pro1
PASSWORD=pro1
PORT=3306
HOST=localhost


echo 'resetCounter Starts...'

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "ALTER TABLE AddressMap AUTO_INCREMENT = 1; ALTER TABLE EmailContactMap AUTO_INCREMENT = 1; ALTER TABLE PhoneContactMap AUTO_INCREMENT = 1; ALTER TABLE SocialContactMap AUTO_INCREMENT = 1; ";

echo 'resetCounter ends...'

