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
import java.util.ArrayList
import java.util.List as List
import java.util.Map as Map
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty

'Set the Web Service header with the security token'
def SetWebServiceHeader() {
	'Set the Web Service call'
	GlobalVariable.policyNumber = findTestData('PolicyByProductCode').getValue('PolicyNo', GlobalVariable.row)
	RequestObject webLogin = findTestObject('WebServices/FetchPayer', ['policyNumber' : GlobalVariable.policyNumber])
	
	'Create new arrayList'
	ArrayList<TestObjectProperty> HTTPHeader = new ArrayList<TestObjectProperty>()

	'Send token in HTTP Header'
	HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, GlobalVariable.bearerKey.toString()))

	'Set the token'
	webLogin.setHttpHeaderProperties(HTTPHeader)

	return webLogin
}

def ListOfMapsToMap(ArrayList listOfMaps) {
	Map toReturn = [:]
	for (Map m : listOfMaps) {
		toReturn[m['fieldKey']] = m['fieldValue']
	}
	return toReturn
}

def CheckRuntimes(ResponseObject response) {
	'Test the runtime'
	println('Elapsed time is: '+response.elapsedTime)
	println('Waiting time is: '+response.waitingTime)
	}

'Check Data in inTouch'
def CheckDataInInTouch(String policyNumber) {
	'Open InTouch to check the data integrity'
	WebUI.callTestCase(findTestCase('InTouch/OpenPolicy'), [:], FailureHandling.STOP_ON_FAILURE)
	//WebUI.getAllLinksOnCurrentPage(false)
	WebUI.navigateToUrl('http://cg-t-iis-01v:88/PayerDetails.aspx')
	
	'Map the Member fields'
	Map inTouchMemberDetails = [
		//'Payer.0.Title' : WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/select_PayerTitle'), 'value'),
		//String Payer.0.Gender': WebUI.getText(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/td_Please SelectGender')),
		'Payer.0.FirstNames': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerFirstNames'),'value'),
		'Payer.0.DateOfBirth': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerDate Of Birth'),'value'),
		'Payer.0.Surname': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerSurname'),'value'),
		'Payer.0.IdentityNumber': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerIDNumber'),'value'),
		'Payer.0.PassportNumber': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerPassportNumber'),'value'),
		//String Payer.0.PassportExpiryDate': WebUI.getText(),
		'Payer.0.EmailAddress': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerEmailAddress'),'value'),
		//String Payer.0.Occupation': WebUI.getfindTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/td_PayerDesignation')Text()
		'Payer.0.MobileTelephoneNumber': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerCellNumber'),'value'),
		//'Payer.0.HomeTelephoneCode': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_HomeTelephoneCode')),'value'),
		'Payer.0.HomeTelephoneNumber': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerHomeTelephoneNumber'),'value'),
		//'Payer.0.WorkTelephoneCode': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_WorkTelephoneCode')),'value'),
		'Payer.0.WorkTelephoneNumber': WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/input_PayerWorkTelephoneNumber'),'value')
		]
	
	return inTouchMemberDetails
}


'Fetch the token'
WebUI.callTestCase(findTestCase('DataGate/FetchToken'), [:], FailureHandling.STOP_ON_FAILURE)

'Send the request'
ResponseObject response = WS.sendRequest(SetWebServiceHeader())
def result = response.getResponseText()

'Verify the response was successful'
WS.verifyResponseStatusCode(response, 200)
CheckRuntimes(response)

'Parse the response to Json Map'
def slurper = new JsonSlurper()
def listOfMaps = slurper.parseText(result)
Map parsedJSON = ListOfMapsToMap(listOfMaps)

println(result)

CheckDataInInTouch(GlobalVariable.policyNumber)