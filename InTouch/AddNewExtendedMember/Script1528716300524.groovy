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

String randomName = CustomKeywords.'RandomNameGenerator.randomName'()

String randomSurname = CustomKeywords.'RandomNameGenerator.randomSurname'()

WebUI.callTestCase(findTestCase('OpenPolicy'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.navigateToUrl('http://cg-t-iis-01v:88/ExtendedMembers.aspx')

WebUI.waitForPageLoad(30)

WebUI.sleep(1000)

//WebUI.switchToWindowTitle('Clientèle')
WebUI.waitForElementPresent(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnAddExtendedMember'), 5)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnAddExtendedMember'))

WebUI.selectOptionByValue(findTestObject('InTouchAddExtendedMember/Page_Clientle/select_Title'), 'Mr', true)

WebUI.setText(findTestObject('InTouchAddExtendedMember/Page_Clientle/input_txtFirstName'), randomName)

WebUI.setText(findTestObject('InTouchAddExtendedMember/Page_Clientle/input_txtSurname'), randomSurname)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/input_txtCellNumber'))

WebUI.setText(findTestObject('InTouchAddExtendedMember/Page_Clientle/input_txtCellNumber'), '0815591750')

WebUI.setText(findTestObject('InTouchAddExtendedMember/Page_Clientle/input_txtDOB'), '12/11/1982')

WebUI.click(findTestObject('Object Repository/InTouchAddExtendedMember/Page_Clientle/input_txtCellNumber'))

//WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/td_Waiting Period'))
WebUI.sleep(1000)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/select_Gender'))

WebUI.selectOptionByValue(findTestObject('InTouchAddExtendedMember/Page_Clientle/select_Gender'), 'Male', true)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/select_ddRelationship'))

WebUI.scrollToElement(findTestObject('InTouchAddExtendedMember/Page_Clientle/select_ddRelationship'), 5)

WebUI.selectOptionByValue(findTestObject('InTouchAddExtendedMember/Page_Clientle/select_ddRelationship'), 'Brother', true)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/input_btnFetchRates'))

WebUI.sleep(1000)

WebUI.selectOptionByValue(findTestObject('InTouchAddExtendedMember/Page_Clientle/select_PlanRate'), '5000', false)

WebUI.waitForElementVisible(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnSaveDetails'), 5)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnSaveDetails'))

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/strong_Extended Member saved s'))

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnOK'))

WebUI.sleep(1000)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnSave'))

WebUI.sleep(1000)

WebUI.waitForElementVisible(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_Okbutton'), 10)

//WebUI.verifyTextPresent('Extended Member Details Staged Successfully', false, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_Okbutton'))

WebUI.sleep(1000)

WebUI.waitForElementPresent(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnOK'), 10)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnOK'))

WebUI.sleep(1000)

WebUI.waitForPageLoad(15)

WebUI.waitForElementPresent(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnFinalize'), 10)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnFinalize'))

WebUI.sleep(1000)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_btnSave'))

WebUI.sleep(1000)

WebUI.waitForElementVisible(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_Okbutton'), 10)

WebUI.verifyTextPresent('Policy Details Saved Successfully', false, FailureHandling.STOP_ON_FAILURE)

WebUI.sleep(3000)

WebUI.click(findTestObject('InTouchAddExtendedMember/Page_Clientle/button_Okbutton'))

