import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.testobject.ConditionType as ConditionType
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
import java.net.Authenticator.RequestorType as RequestorType
import java.util.List as List
import java.util.Map as Map

import javax.media.StopEvent

import org.junit.After

import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty

def MapWebApiBodyVariables(int rowId) {
	
return findTestObject('WebServices/ExternalSales/CheckSale',
	[('EmailAddress'):findTestData('CheckSale').getValue('EmailAddress', rowId),
	('ProductCode'):findTestData('CheckSale').getValue('ProductCode', rowId),
	('ApplicationNumber'):findTestData('CheckSale').getValue('ApplicationNumber', rowId),
	('AdministratorBrokerCode'):findTestData('CheckSale').getValue('AdministratorBrokerCode', rowId),
	('AdministratorBrokerPassword'):findTestData('CheckSale').getValue('AdministratorBrokerPassword', rowId)])
}

def SetWebServiceHeader() {
	int rowId = 1
	
	WebUI.callTestCase(findTestCase('DataGate/FetchToken'), [:], FailureHandling.STOP_ON_FAILURE)
	
	'Set the Web Service call'
	def webServiceBody = MapWebApiBodyVariables(rowId)
	RequestObject webLogin = webServiceBody

	'Create new arrayList'
	ArrayList<TestObjectProperty> HTTPHeader = new ArrayList<TestObjectProperty>()

	'Send token in HTTP Header'
	HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, GlobalVariable.bearerKey.toString()))

	'Set the token'
	webLogin.setHttpHeaderProperties(HTTPHeader)

	return webLogin
}

def CheckRuntimes(ResponseObject response) {
	'Test the runtime'
	println('Elapsed time is: '+response.elapsedTime)
	println('Waiting time is: '+response.waitingTime)
	}


'Fetch the token'
//WebUI.callTestCase(findTestCase('DataGate/FetchToken'), [:], FailureHandling.STOP_ON_FAILURE)
int rowId = 1

ResponseObject response = WS.sendRequest(MapWebApiBodyVariables(rowId)) 

//WS.sendRequest(SetWebServiceHeader())

def result = response.getResponseBodyContent()

println result

def slurper = new JsonSlurper()

def listOfMaps = slurper.parseText(result)
//Map parsedJSON = ListOfMapsToMap(listOfMaps)

CheckRuntimes(response)

'Verify an OK response'
WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)
println('Response Status code is: '+response.statusCode)
