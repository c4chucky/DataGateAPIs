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


def SetWebServiceHeader() {
	
	'Set the Web Service call'
	//GlobalVariable.policyNumber = findTestData('PolicyByProductCode').getValue('PolicyNo', GlobalVariable.row)
	RequestObject webLogin = findTestObject('WebServices/RegisterNewUser')

	'Create new arrayList'
	ArrayList<TestObjectProperty> HTTPHeader = new ArrayList<TestObjectProperty>()

	'Send token in HTTP Header'
	HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, GlobalVariable.bearerKey.toString()))

	'Set the token'
	webLogin.setHttpHeaderProperties(HTTPHeader)

	return webLogin
}

'Fetch the token'
WebUI.callTestCase(findTestCase('DataGate/FetchToken'), [:], FailureHandling.STOP_ON_FAILURE)

'Send the request'
ResponseObject response = WS.sendRequest(SetWebServiceHeader())
def result = response.getResponseText()

'Verify the response was successful'
WS.verifyResponseStatusCode(response, 200)

'Parse the response to Json Map'
def slurper = new JsonSlurper()
def listOfMaps = slurper.parseText(result)
//Map parsedJSON = ListOfMapsToMap(listOfMaps)

println(result)
//println parsedJSON

