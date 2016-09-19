




echo $PATH
DB_PATH=/tmp/applifire/db/LYQXYHJJYFNYLVKV3KLBVQ
MYSQL=/usr/bin
USER=pro3
PASSWORD=pro3
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'