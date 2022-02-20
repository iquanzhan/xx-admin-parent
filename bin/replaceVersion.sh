#!/bin/bash
#-----------------------------------------------------------
# 此脚本用于每次升级时替换相应位置的版本号
#-----------------------------------------------------------

set -o errexit

pwd=$(pwd)

echo "当前路径：${pwd}"

if [ -n "$1" ];then
    new_version="$1"
    echo "替换为新版本 $new_version"
else
    # 参数错误，退出
    echo "ERROR: 请指定新版本！"
    exit
fi

# 替换所有模块pom.xml中的版本
cd ../ && mvn versions:set -DnewVersion=$1

