smoke_test_prefix = "Production Smoke Tests "
testcase_type_map = [
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

def testcase_data_mapping(testcase)
{
    testcase_type = testcase.@name.toString().replace(smoke_test_prefix, "")
    testcase_type_map.each { key, val ->
        if (testcase_type.startsWith(key))
        {
            if (!testcase.failure.isEmpty())
            {
                testcase_type_map[key]["test_result"] = "Fail"
                testcase_type_map[key]["fail_test_num"] += 1
            }
            testcase_type_map[key]["total_test_num"] += 1
            return
        }
    }
}

def test_data_report()
{
    message = ""
    testcase_type_map.each{ key, val ->
        num_passed = val["total_test_num"] - val["fail_test_num"]
        num_failed = val["fail_test_num"]
        message += "${key} [Failed]: ${num_failed} [Passed]: ${num_passed}\n"
    }
    return message
}

String fileContents = new File('/var/jenkins_home/workspace/hello_world_1/smoke_test_result.xml').getText('UTF-8')
def response = new XmlSlurper().parseText(fileContents)
def testcases = response.testcase
def message = ""
testcases.each { testcase -> 
testcase_data_mapping(testcase)
 }
test_report = test_data_report()
println test_report
