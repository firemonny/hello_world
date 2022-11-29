def testcase_Parsing(testcase) {
    
    if (testcase.failure)
    {
        return testcase.failure.@message
    }
    else
    {
        println testcase.@name
        return "${testcase.@name}: [Passed] \n"
    }
}


String fileContents = new File('/var/jenkins_home/workspace/hello_world_1/smoke_test_result.xml').getText('UTF-8')
def response = new XmlSlurper().parseText(fileContents)
def testcases = response.testcase
def message = ""
testcases.each { testcase -> 
append_message = testcase_Parsing(testcase)
message += append_message }
print message
