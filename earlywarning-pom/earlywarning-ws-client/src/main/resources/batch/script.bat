@echo off

set WSDLURL=
set WORKFLOW_NAME=Update_Nightly

java -jar client.jar %WSDLURL% %WORKFLOW_NAME%