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
	WebUI.navigateToUrl('http://cg-t-iis-01v:88/PayerDetails.aspx')
	
	'Map the Member fields'
	Map inTouchMemberDetails = [
		'Payer.0.Title' : WebUI.getAttribute(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PayerDetail/select_PayerTitle'), 'value'),
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
	
	for (String s : inTouchMemberDetails) {
		println s
	}