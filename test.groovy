smoke_test_prefix = "Production Smoke Tests "
testcase_type_map = [
    "creates a windows policy with default setting and group": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "downloads connector, register and send events": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "dashboard and Events page": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "dashboard and overview page": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "File Trajectory": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "Device Trajectory": ["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "computer page searches":["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "Pagination on Events and Computers Page by using Demo Data":["test_result":"Passed","total_test_num":0,"fail_test_num":0],
    "Testing public APIs (computers, policies, groups, events)":["test_result":"Passed","total_test_num":0,"fail_test_num":0]
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
        if(val["fail_test_num"] > 0)
        {
            message += "${key.capitalize()} [Failed]: ${val["fail_test_num"]}\n"
        }
        else
        {
            message += "${key.capitalize()} [Passed]\n"
        }
    }
    return message
}

String fileContents = new File('/var/jenkins_home/workspace/hello_world_1/fail_example_result.xml').getText('UTF-8')
def response = new XmlSlurper().parseText(fileContents)
def testcases = response.testcase
def message = ""
testcases.each { testcase -> 
testcase_data_mapping(testcase)
 }
test_report = test_data_report()
println test_report
