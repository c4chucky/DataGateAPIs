import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType as ConditionType

int randomApplicationNumber = Math.random()*10000000
String applicationNumber = 'BA'+randomApplicationNumber.toString();
println applicationNumber

WebUI.callTestCase(findTestCase('DataGate/PolicyCreate/TestThirdPartyLogin_Web'), [:], FailureHandling.STOP_ON_FAILURE)

OpenSale(MapApiResponseObject(applicationNumber))


def FetchToken(String applicationNumber) {
	'Set the Web Service call'
	println MapApiResponseObject(applicationNumber)
	RequestObject webLogin = MapApiResponseObject(applicationNumber)
	
	'Create new arrayList'
	ArrayList<TestObjectProperty> HTTPHeader = new ArrayList<TestObjectProperty>()
	
	'Send token in HTTP Header'
	HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, GlobalVariable.bearerKey))
	
	'Set the token'
	return webLogin.setHttpHeaderProperties(HTTPHeader)	

}

def OpenSale(RequestObject FetchToken) {
	def response = WS.sendRequest(FetchToken);
	
	println response.getResponseText();
	
	JsonSlurper slurper = new JsonSlurper();
	Map parsedJson = slurper.parseText(response.getResponseText());
	
	def saleId = parsedJson.SaleId;
	println 'SaleID: '+saleId
	
	WS.verifyResponseStatusCode(response, 200)
		
	GlobalVariable.SaleId = saleId
	
	GlobalVariable.SaleJson = parsedJson
	
	/*println "jsonObject is of type : " +  parsedJson.getClass()
	println "jsonObject is a Map ? " + (parsedJson instanceof Map)*/
	assert(parsedJson instanceof Map)
}

RequestObject MapApiResponseObject(String applicationNumber) {
	int row = GlobalVariable.row
return findTestObject('Object Repository/PolicyCreate/1. OpenSale',
	[("emailAddress"): findTestData('Data Files/SaveSale/Member').getValue('EmailAddress', row),
	("productCode"): findTestData('Data Files/SaveSale/SaveSale').getValue('ProductCode', row),
	("applicationNumber"):applicationNumber,
	("administratorBrokerCode"):"B1234",
	("administratorBrokerPassword"):"0644160204"])
	/*("IfaOnly"): "Yes",
	("IsIfa"): "Yes"])*/
  }
  

