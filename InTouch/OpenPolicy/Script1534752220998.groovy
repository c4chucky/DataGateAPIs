import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import org.openqa.selenium.Keys as Keys

//int row = 8

String policyNumber = findTestData('PolicyByProductCode').getValue('PolicyNo', GlobalVariable.row)

def modalPopUp = findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_modalbuttonOK - Copy')

WebUI.openBrowser('')

WebUI.navigateToUrl('http://cg-t-iis-01v:88/')

WebUI.setText(findTestObject('InTouchAddExtendedMember/Page_Clientle/input_ctl00BodyPlaceHolderTabC'), policyNumber)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/input_ctl00BodyPlaceHolderTabC_1'))

waitForLoadingModal(5)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/a_lnkLifeAssured'))

WebUI.waitForPageLoad(30)

waitForLoadingModal(5)

WebUI.verifyTextPresent(policyNumber, false)

def waitForLoadingModal(int seconds) {
	WebUI.waitForElementNotPresent(findTestObject('Object Repository/InTouchAddExtendedMember/modal_LoadingImage'), seconds)
}