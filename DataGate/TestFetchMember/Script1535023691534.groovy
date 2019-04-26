import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager as WSResponseManager
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
//import jsonSerializationClasses.CaptureFields as CaptureFields
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
	GlobalVariable.policyNumber = findTestData('PolicyByProductCode').getValue('PolicyNo', GlobalVariable.row)
	RequestObject webLogin = findTestObject('WebServices/FetchMember', ['policyNumber' : GlobalVariable.policyNumber])

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

'Map the JSON values to keys'
def ListOfMapsToMap(ArrayList jsonArray) {
	Map toReturn = [:]
	for (Map m : jsonArray) {
		toReturn[m['fieldKey']] = m['fieldValue']
	}
	return toReturn
}

'Check Data in inTouch'
def CheckDataInInTouch(String policyNumber) {
	'Open InTouch to check the data integrity'
	WebUI.callTestCase(findTestCase('InTouch/OpenPolicy'), [:], FailureHandling.STOP_ON_FAILURE)
	//WebUI.getAllLinksOnCurrentPage(false)
	WebUI.navigateToUrl('http://cg-t-iis-01v:88/MemberDetails.aspx')
	
	'Map the Member fields'
	Map inTouchMemberDetails = [
		'Member.0.Title' : WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/Member_Title'), 'value'),
		//String Member.0.Gender': WebUI.getText(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/td_Please SelectGender')),
		'Member.0.FirstNames': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_FirstNames')),'value'),
		'Member.0.DateOfBirth': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_DateOfBirth')),'value'),
		'Member.0.Surname': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_Surname')),'value'),
		'Member.0.IdentityNumber': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_IdentityNumber')),'value'),
		'Member.0.PassportNumber': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_PassportNumber')),'value'),
		//String Member.0.PassportExpiryDate': WebUI.getText(),
		'Member.0.EmailAddress': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_EmailAddress')),'value'),
		//String Member.0.Occupation': WebUI.getText(),
		'Member.0.PostalAddressLine1': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_PostalAddressLine1')),'value'),
		'Member.0.PostalAddressLine2': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_PostalAddressLine2')),'value'),
		'Member.0.PostalAddressLine3': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_PostalAddressLine3')),'value'),
		'Member.0.PostalAddressCode': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_PostalAddressCode')),'value'),
		'Member.0.ResidentialAddressLine1': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_ResidentialAddressLine1')),'value'),
		'Member.0.ResidentialAddressLine2': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_ResidentialAddressLine2')),'value'),
		'Member.0.ResidentialAddressLine3': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_ResidentialAddressLine3')),'value'),
		'Member.0.ResidentialAddressCode': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_ResidentialAddressCode')),'value'),
		'Member.0.MobileTelephoneNumber': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_MobileTelephoneNumber')),'value'),
		'Member.0.HomeTelephoneCode': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_HomeTelephoneCode')),'value'),
		'Member.0.HomeTelephoneNumber': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_HomeTelephoneNumber')),'value'),
		'Member.0.WorkTelephoneCode': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_WorkTelephoneCode')),'value'),
		'Member.0.WorkTelephoneNumber': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_WorkTelephoneNumber')),'value')
		//'Member_FaxNumber': WebUI.getText()
		]
	
	return inTouchMemberDetails
}

'Verify the response equals the expectedData'
def VerifyResponseFields(Map receivedData, Map expectedData) {
	'Verify the JSON elements'
	//WS.containsString(response, "Mr", false)
/*
	String key = 'Member.0.Title'	
	String value = 'Mr'
	
	if (receivedData[key] == value)
	{
		println 'Yay it passed!'
	}
	else
	{
		println 'Oh no, it failed!'
	}*/
	
	
	
/*	for (def keyValuePair : expectedData)
	{
		boolean check = receivedData[keyValuePair.key] == keyValuePair.value
		if (check == false)
		{
			println 'Something failed on ' + keyValuePair.key
		}
	}*/
	Map results = [:]
	for (def keyValuePair : receivedData)
	{
		boolean check = expectedData[keyValuePair.key] == keyValuePair.value
		results[keyValuePair.key] = check
		if (!check) {
			println('!!!!!!!!!!!!!!! VERIFICATION FAILED AT: '+results)
			println(expectedData[keyValuePair.key])+' is not equal to '+(keyValuePair.value)
			FailureHandling.STOP_ON_FAILURE
			//StopEvent
		}
	}
	
	println results
	return results
}	


'Fetch the token'
WebUI.callTestCase(findTestCase('DataGate/FetchToken'), [:], FailureHandling.STOP_ON_FAILURE)

ResponseObject response = WS.sendRequest(SetWebServiceHeader())

def result = response.getResponseBodyContent()

println result

def slurper = new JsonSlurper()

def listOfMaps = slurper.parseText(result)
Map parsedJSON = ListOfMapsToMap(listOfMaps)

CheckRuntimes(response)

'Verify an OK response'
WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)
println('Response Status code is: '+response.statusCode)

'Verify the JSON values with inTouch values'
Map inTouchMemberDetails = CheckDataInInTouch(GlobalVariable.policyNumber)
VerifyResponseFields(inTouchMemberDetails, parsedJSON)



