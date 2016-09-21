




echo $PATH
DB_PATH=/tmp/applifire/db/ASTEVIR12GSVBKRFWCVH2Q
MYSQL=/usr/bin
USER=pro1
PASSWORD=pro1
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'