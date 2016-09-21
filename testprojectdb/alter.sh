




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


