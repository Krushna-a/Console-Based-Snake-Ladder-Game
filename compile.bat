@echo off
echo Compiling Snake and Ladder Game...
javac -d bin src\models\*.java src\game\*.java src\main\*.java
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
) else (
    echo Compilation failed!
)
pause
