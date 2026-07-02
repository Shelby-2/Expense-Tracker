@echo off

set "FX=D:\javafx21\javafx-sdk-21.0.11\lib"
set "JDBC=D:\Me\Projects\Java codes\Projects\Expense Tracker\lib\mssql-jdbc-13.4.0.jre11.jar"

javac --module-path "%FX%" --add-modules javafx.controls -cp "%JDBC%" -d out src\Main.java src\UI\*.java src\model\*.java src\database\*.java

java --module-path "%FX%" --add-modules javafx.controls -cp "out;%JDBC%" Main

pause