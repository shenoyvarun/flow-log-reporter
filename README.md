# flow-log-reporter

To compile & run (java17):
1. Generate flow_logs.log (Creates a file >50MB in /data_sources with randomized values for all fields)
    
    javac src/GenerateFlowLogs.java
    
    java src/GenerateFlowLogs
2. Run Reports
    
    javac src/FlowLogReportingSystem.java
    
    java src/FlowLogReportingSystem
3. Check Generated Reports in /reports

Note: If testing is needed with a particular file template, add a file with name 'flow_logs.log' in /data_sources and directly run Step 2 above 
   OR
      Replace value of FLOW_LOG_FILE in src/Utilities.java with the file name of your choice and then run Step 2

Assumptions:
Following validations have been put in place:
1. If the version is not an integer, default to 1
2. If the src/dest port < 0 || port > 65535, default to 0
3. If the protocol is not in (1,6,17), default to 6 (tcp)
4. Protocol assumptions: 6 = tcp, 17 = udp, 1 = icmp
5. All above validations, including if integer/long values are un-parseable, Log a Warning (code execution will not stop)

Tests:
1. Multiple versions
2. Insensitive Case
3. Unparseable data. Eg: version = "abc"
4. Ran with data_sources/flow_logs_1.log to test logic with all scenarios 
5. Using src/GenerateFlowLogs to create a flow_logs.log file > 50MB to Load Test
