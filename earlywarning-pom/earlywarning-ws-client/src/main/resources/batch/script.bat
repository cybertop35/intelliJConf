@echo off

set WSDLURL=http://salcls0333.sede.corp.sanpaoloimi.com:8524/aaap/executeDailyBatchWS/executeDailyBatch
set WORKFLOW_NAME=Update_Nightly

java -jar client.jar %WSDLURL% %WORKFLOW_NAME%