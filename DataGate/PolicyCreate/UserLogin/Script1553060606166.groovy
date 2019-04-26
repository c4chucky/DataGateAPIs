import static org.assertj.core.api.Assertions.*
import javax.media.rtp.GlobalReceptionStats as GlobalReceptionStats
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager as WSResponseManager
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType as ConditionType

//ResponseObject response = WS.sendRequest(findTestObject('WebServices/TestUserLogin_WS', ['Authorization' : ]))
//def webLogin = WebUI.modifyObjectProperty(findTestObject('WebServices/TestUserLogin_WS'), 'Authorization', 'equals', GlobalVariable.bearerKey, false)
'Set the Web Service call'
RequestObject webLogin = findTestObject('WebServices/TestUserLogin_WS')

'Create new arrayList'
ArrayList<TestObjectProperty> HTTPHeader = new ArrayList<TestObjectProperty>()

'Send token in HTTP Header'
HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, GlobalVariable.bearerKey))

'Set the token'
webLogin.setHttpHeaderProperties(HTTPHeader)

ResponseObject response = WS.sendRequest(webLogin)

GlobalVariable.bearerKey = (response.getHeaderFields().get('Authorization')[0])

println(GlobalVariable.bearerKey)

def result = response.getResponseText()

//WS.verifyResponseStatusCode(response, 200)

/*WS.verifyElementPropertyValue(response, 'FirstNames', 'Larrie')

WS.verifyElementPropertyValue(response, 'Surname', 'Ngcobo')
*/
println(('Response waiting time is ' + response.getWaitingTime()) + ' miliseconds')

println(result)

def slurper = new JsonSlurper()

def resultJSON = slurper.parseText(response.getResponseBodyContent())

println(resultJSON)

