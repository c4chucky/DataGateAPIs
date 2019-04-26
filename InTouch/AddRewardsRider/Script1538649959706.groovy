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


//Open policyQuery
WebUI.callTestCase(findTestCase('InTouch/OpenPolicy'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('page_inTouch_PolicySearch/Page_BenefitDetail/div_Benefit Details'))

waitForLoadingModal(1)

WebUI.click(findTestObject('page_inTouch_PolicySearch/Page_BenefitDetail/button_EditBenefitDetail'))

waitForLoadingModal(1)

WebUI.click(findTestObject('page_inTouch_PolicySearch/Page_BenefitDetail/select_Rewards - (VC)'))

WebUI.selectOptionByValue(findTestObject('page_inTouch_PolicySearch/Page_BenefitDetail/select_Rewards - (VC)'), 'VC01', false)

WebUI.click(findTestObject('page_inTouch_PolicySearch/Page_BenefitDetail/button_GetRates'))

waitForLoadingModal(1)

WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/select_RiderRate'))

WebUI.selectOptionByIndex(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/select_RiderRate'), 0)

WebUI.click(findTestObject('page_inTouch_PolicySearch/Page_BenefitDetail/button_AddRider'))

waitForLoadingModal(1)

WebUI.verifyTextPresent('Benefit has been added.', false)

WebUI.click(findTestObject('Object Repository/page_inTouch_PolicySearch/Page_BenefitDetail/button_modalbuttonOK - Copy'))

//Finalize the changes
WebUI.callTestCase(findTestCase('InTouch/FinalizePolicyChanges'), [:], FailureHandling.STOP_ON_FAILURE)


def waitForLoadingModal(int seconds) {
	//WebUI.waitForElementNotPresent(findTestObject('Object Repository/InTouchAddExtendedMember/modal_LoadingImage'), seconds)
	WebUI.waitForElementNotPresent(findTestObject('Object Repository/page_inTouch_PolicySearch/image_loading'), seconds)
}

