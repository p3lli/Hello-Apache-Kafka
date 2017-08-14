#!/usr/bin/env bash

wget $1
FILE_GZ="$(echo $1 | rev | cut -d'/' -f 1 | rev)"
tar -zxf $FILE_GZ -C ../
mv ../kafk* ../kafka
rm -rf $FILE_GZ
