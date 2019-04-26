import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.ArrayList

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

	WebUI.callTestCase(findTestCase('InTouch/OpenPolicy'), [:], FailureHandling.STOP_ON_FAILURE)
	//WebUI.getAllLinksOnCurrentPage(false)
	WebUI.navigateToUrl('http://cg-t-iis-01v:88/MemberDetails.aspx')
	
	'Map the Member fields'
	Map inTouchMemberDetails = ['Member.0.Title' : WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/Member_Title'), 'value'),
		//String Member.0.Gender': WebUI.getText(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/td_Please SelectGender')),
		'Member.0.FirstNames': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_FirstNames')),'value'),
		'Member.0.DateOfBirth': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_DateOfBirth')),'value'),
		'Member.0.Surname': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_Surname')),'value'),
		'Member.0.IdentityNumber': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_IdentityNumber')),'value'),
		'Member.0.PassportNumber': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_PassportNumber')),'value'),
		//String Member.0.PassportExpiryDate': WebUI.getText(),
		'Member.0.EmailAddress': WebUI.getAttribute((findTestObject('Object Repository/page_inTouch_PolicySearch/Page_MemberDetail/input_Member_EmailAddress')),'value'),
		//String Member.0.Occupation': WebUI.getText()
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
	
	for (String s : inTouchMemberDetails) {
		println s
	}
	
	/*ListOfMapsToMap(inTouchMemberDetails)
	
	def ListOfMapsToMap(ArrayList inTouchMemberDetails) {
		Map toReturn = [:]
		for (Map m : inTouchMemberDetails) {
			toReturn[m['fieldKey']] = m['fieldValue']
		}
		return toReturn
	}*/