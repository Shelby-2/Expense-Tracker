@echo off

javac --module-path "D:\javafx21\javafx-sdk-21.0.11\lib" --add-modules javafx.controls -d out src\Main.java src\UI\*.java src\model\*.java

java --module-path "D:\javafx21\javafx-sdk-21.0.11\lib" --add-modules javafx.controls -cp out Main

pause