import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.util.ArrayList
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.util.List as List
import java.util.Map as Map
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.impl.HttpUrlEncodedBodyContent
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent

//WebUI.callTestCase(findTestCase('DataGate/PolicyCreate/CreatePolicy'), [:], FailureHandling.STOP_ON_FAILURE)

SendAndVerifyRequest(GlobalVariable.SaleJson)

def SendAndVerifyRequest(Map SaleJson) {
/*SaleJson.Premium = 165
SaleJson.SumAssured = 165000*/

JsonSlurper slurper = new JsonSlurper()
	def jsonMap = JsonOutput.toJson(SaleJson)
	
	def req = buildPostApiRequest1(jsonMap)
	
	ResponseObject response = WS.sendRequest(req)
	
	println 'Response: '+response.getResponseText()
	
	println 'Elapsed Time: '+response.getElapsedTime()+'ms'
	
	WS.verifyResponseStatusCode(response, 200)
	WS.containsString(response, 'Accepted', false)
}

def buildPostApiRequest1(String body) {
	
	RequestObject ro = findTestObject('Object Repository/PolicyCreate/3. AcceptSale')
	
	'Create new arrayList'
	ArrayList<TestObjectProperty> HTTPHeader = new ArrayList<TestObjectProperty>()
	
	'Send token in HTTP Header'
	HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, GlobalVariable.bearerKey))
	HTTPHeader.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
	
	ro.setHttpHeaderProperties(HTTPHeader)
	
	ro.setBodyContent(new HttpTextBodyContent(body))
	
	return ro

}