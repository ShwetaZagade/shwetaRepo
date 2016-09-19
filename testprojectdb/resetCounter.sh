




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/LYQXYHJJYFNYLVKV3KLBVQ
ART_CREATE_PATH=/tmp/applifire/db/LYQXYHJJYFNYLVKV3KLBVQ/art/create
AST_CREATE_PATH=/tmp/applifire/db/LYQXYHJJYFNYLVKV3KLBVQ/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=root
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=pro3
USER=pro3
PASSWORD=pro3
PORT=3306
HOST=localhost


echo 'resetCounter Starts...'

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "ALTER TABLE ast_AddressMap_B AUTO_INCREMENT = 1; ALTER TABLE ast_EmailContactMap_B AUTO_INCREMENT = 1; ALTER TABLE ast_PhoneContactMap_B AUTO_INCREMENT = 1; ALTER TABLE ast_SocialContactMap_B AUTO_INCREMENT = 1; ";

echo 'resetCounter ends...'

