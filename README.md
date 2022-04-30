# Operators

compile the files in command line:

javac .\Operators.java .\OperatorsTest.java .\TestRunner.java

***** RUNNING THE PROGRAM *****

run the program in command line:

java .\Operators.java

enter your operation as follow containing at least one whitespace between:

? WHOLE1_NUMERATOR1/DENOMINATOR1 (+ | * | - | /) WHOLE2_NUMERATOR2/DENOMINATOR2

***** TESTING THE PROGRAM *****

run the test runner as follow in command line:

c:; cd '<ABS_PATH_TO_CURRENT_FOLDER>'; & '<ABS_PATH_TO_JAVA_EXE_FILE' '--enable-preview' 
-cp "<ABS_PATH_TO_CURRENT_FOLDER>;<ABS_PATH_TO_HAMCREST_CORE_JAR>;<ABS_PATH_TO_JUNIT_JAR>" 'TestRunner'

test cases are supplied in OperatorsTest.java file as arrays element as following format:

{ WHOLE1, NUMERATOR1, DENOMINATOR1, WHOLE2, NUMERATOR2, DENOMINATOR2, OPERATOR, EXPECTED_RESULT[NULL if not supplied] }

{ 3, 3, 4, 0, 1, 2, "*", null }

Please note the following for above commands:
* "Denominators can't be zero and if the operation is division, second numerator can't be zero either"
* "Choosing large values might lead to overflow condition and return wrong results"





