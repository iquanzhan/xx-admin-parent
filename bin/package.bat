@echo off
echo.
echo [��Ϣ] ���Web���̣�����jar���ļ���
echo.

%~d0
cd %~dp0

cd ..
call mvn clean package -Dmaven.test.skip=true

pause