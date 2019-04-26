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

//Finalize the changes
//WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_modalbuttonOK - Copy'))

WebUI.click(findTestObject('page_inTouch_PolicySearch/Page_BenefitDetail/button_Save'))

waitForLoadingModal(1)

WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_modalbuttonOK'))

waitForLoadingModal(1)

WebUI.verifyTextPresent('Policy Benefits Staged Successfully', false)

WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_modalbuttonOK - Copy'))

WebUI.click(findTestObject('Object Repository/InTouchAddExtendedMember/Page_Clientle/button_btnFinalize'))

WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_PolicyRates/button_Accept'))

waitForLoadingModal(1)

WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_modalbuttonOK'))

waitForLoadingModal(5)

WebUI.verifyTextPresent('Policy Details Saved Successfully', false)

WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_modalbuttonOK - Copy'))

waitForLoadingModal(1)

/*WebUI.scrollToElement(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_ExitPolicyQuery'), 1)

WebUI.waitForElementVisible(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_ExitPolicyQuery'), 0)*/

WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_Exit'))

waitForLoadingModal(1)

def waitForLoadingModal(int seconds) {
	//WebUI.waitForElementNotPresent(findTestObject('Object Repository/InTouchAddExtendedMember/modal_LoadingImage'), seconds)
	WebUI.waitForElementNotVisible(findTestObject('Object Repository/page_inTouch_PolicySearch/image_loading'), seconds)
}