def smoke_test_prefix = "Production Smoke Tests "
def testcase_type_map = [
    "creates a windows policy with default setting and group": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "downloads connector, register and send events": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "on Dashboard Events page": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "on Dashboard, Overview page": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "File Trajectory": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "Device Trajectory": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "computer page searches":["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "pagination in Events and Computers":["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "testing some public API":["test_result":"Passed","total_test_num":0,"fail_test_num":0]
    ]

def testcase_Parsing(testcase) {
    testcase_type = testcase.@name.replace(smoke_test_prefix, "")
    println(testcase_type)
    println(testcase.@name)
    if (!testcase.failure.isEmpty())
    {
        return "${testcase.@name}: [Failed] ${testcase.failure.@message}\n"
    }
    else
    {
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
println message
