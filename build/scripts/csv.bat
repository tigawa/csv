@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  csv startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and CSV_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xms32m" "-Xmx32m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\csv-1.0.jar;%APP_HOME%\lib\commons-collections-3.2.jar;%APP_HOME%\lib\orangesignal-csv-2.0.0.jar;%APP_HOME%\lib\stubby4j-5.1.1.jar;%APP_HOME%\lib\jetty-server-9.4.8.v20171121.jar;%APP_HOME%\lib\jsonassert-1.3.0.jar;%APP_HOME%\lib\jetty-servlets-9.4.8.v20171121.jar;%APP_HOME%\lib\commons-cli-1.2.jar;%APP_HOME%\lib\xmlunit-1.6.jar;%APP_HOME%\lib\snakeyaml-1.17.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\collection-type-safe-converter-1.0.1.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\jetty-http-9.4.8.v20171121.jar;%APP_HOME%\lib\jetty-io-9.4.8.v20171121.jar;%APP_HOME%\lib\json-20090211.jar;%APP_HOME%\lib\jetty-continuation-9.4.8.v20171121.jar;%APP_HOME%\lib\jetty-util-9.4.8.v20171121.jar

@rem Execute csv
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CSV_OPTS%  -classpath "%CLASSPATH%" org.gradle.CsvWriter %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable CSV_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%CSV_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
