@echo off
set script_dir=%~dp0
set working_dir=%cd%
if "%working_dir%" neq "%script_dir%" cd /d "%script_dir%"
if exist ".\build\libs\L2JStatsService.jar" (
    echo L2JStatsService.jar exists
    java -jar ".\build\libs\L2JStatsService.jar"
) else (
    echo L2JStatsService.jar does not exist
)
