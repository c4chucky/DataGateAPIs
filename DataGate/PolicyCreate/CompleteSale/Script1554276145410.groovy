import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.ArrayList

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

//WebUI.callTestCase(findTestCase("DataGate/PolicyCreate/AcceptSale"), [:], FailureHandling.STOP_ON_FAILURE)

String endpoint = "https://rest-test.clientele.co.za/api/sale/complete/"+GlobalVariable.SaleId
String requestMethod = "POST"
String authHeader = GlobalVariable.bearerKey

TestObjectProperty header1 = new TestObjectProperty("Authorization", ConditionType.EQUALS, authHeader)
TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")

ArrayList defaultHeaders = Arrays.asList(header1, header2)
String body = ""

def resp = buildApiRequest1(endpoint, defaultHeaders, requestMethod)

println resp.getStatusCode()
println resp.getResponseText()

JsonSlurper slurper = new JsonSlurper()
def response = slurper.parseText(resp.getResponseText())

GlobalVariable.SaleJson = response
GlobalVariable.result = response.PolicyNumber
println 'The PolicyNumer is: '+response.PolicyNumber


public ResponseObject buildApiRequest1(String endpoint, ArrayList defaultHeaders, String requestMethod) {
	RequestObject ro = new RequestObject("CompleteSale")
	ro.setRestUrl(endpoint)
	ro.setHttpHeaderProperties(defaultHeaders)
	ro.setRestRequestMethod(requestMethod)
	ro.setBodyContent(new HttpTextBodyContent(""))
	
	ResponseObject respObj = WS.sendRequest(ro)
	return respObj
}
