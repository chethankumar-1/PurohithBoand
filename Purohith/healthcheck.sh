#!/bin/bash
# exits 0 if Oracle is ready
sqlplus system/"$ORACLE_PASSWORD"@//localhost:1521/XEPDB1 <<EOF
exit
EOF