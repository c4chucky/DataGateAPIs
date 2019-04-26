import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.util.ArrayList
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
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
import java.util.List as List
import java.util.Map as Map
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.impl.HttpUrlEncodedBodyContent
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent

Map SaleJson = GlobalVariable.SaleJson

int allRows = findTestData('Data Files/SaveSale/SaveSale').getRowNumbers()
GlobalVariable.row = 1
int column = 8
String result = ""
ArrayList results = []


for (GlobalVariable.row; GlobalVariable.row<=allRows;GlobalVariable.row++) {
	WebUI.callTestCase(findTestCase('DataGate/PolicyCreate/OpenSale'), [:], FailureHandling.STOP_ON_FAILURE)
	SendAndVerifyRequest(SaleJson, GlobalVariable.row, column)
	if(GlobalVariable.result == []) {
		
		WebUI.callTestCase(findTestCase("DataGate/PolicyCreate/AcceptSale"), [:], FailureHandling.STOP_ON_FAILURE)
		WebUI.callTestCase(findTestCase("DataGate/PolicyCreate/CompleteSale"), [:], FailureHandling.STOP_ON_FAILURE)
	}
	//results.add(GlobalVariable.result)
	CustomKeywords.'mypackage.WriteExcel.demoKey'(GlobalVariable.result.toString(), GlobalVariable.row, column)
	Thread.sleep(2000)
}


CustomKeywords.'mypackage.MoveDataFileToProcessed.moveDataFileToProcessed'()

def SendAndVerifyRequest(Map SaleJson, int row, int column) {
	//int row = 1
	SaleJson.SaleId = GlobalVariable.SaleId
	SaleJson.Premium = findTestData('Data Files/SaveSale/SaveSale').getValue('Premium', row)
	SaleJson.SumAssured = findTestData('Data Files/SaveSale/SaveSale').getValue('SumAssured', row)
	SaleJson.Member = [BuildPersonJson("Member")]
	SaleJson.Payer = [BuildPersonJson("Payer")]
	SaleJson.Beneficiary = [BuildPersonJson("Beneficiary")]
	//SaleJson.Spouse = [BuildPersonJson("Spouse")]
	
	/*SaleJson.put("IfaOnly", "Yes")
	SaleJson.put("IsIfa", "Yes")*/
	
	
	JsonSlurper slurper = new JsonSlurper()
	def jsonMap = JsonOutput.toJson(SaleJson)
	
	println GlobalVariable.bearerKey
	println 'Json with Member details: '+jsonMap
	
	ResponseObject response = WS.sendRequest(buildPostApiRequest1(jsonMap))
	
	Map parsedJson = slurper.parseText(response.getResponseText());
	//println parsedJson
	
	if (parsedJson.ErrorMessages[0] == "") {
		GlobalVariable.errorMessage = ""
	} else {
		GlobalVariable.errorMessage = parsedJson.ErrorMessages
	}
	
	GlobalVariable.result = GlobalVariable.errorMessage
	
	println 'Response: '+response.getResponseText()
	
	println 'Elapsed Time: '+response.getElapsedTime()+'ms'
	
	WS.verifyResponseStatusCode(response, 200)
	
	GlobalVariable.SaleJson = parsedJson

}

def BuildPersonJson(String Person) {
	ArrayList listOfColNamesAndValues = []
	Map mappedColNamesAndValues = [:]
	String value = ""
	ArrayList rowValues = []
	
		def colNames = findTestData("SaveSale/"+Person).getColumnNames()
		//println colNames
		
		def allValues = findTestData("SaveSale/"+Person).getAllData()
		
		//for (int row=GlobalVariable.row;row<=findTestData("SaveSale/"+Person).getRowNumbers();row++) {
			for (int index=0;index<colNames.length;index++) {
				value = findTestData("SaveSale/"+Person).getValue(colNames[index], GlobalVariable.row)
				rowValues.add(value)
				listOfColNamesAndValues = [colNames,rowValues].transpose()
				
			}

			//println 'rowValues are: '+rowValues
		
		//}
		
		'Map the values to keys'
		for (int i=0;i<listOfColNamesAndValues.size();i++) {
			listOfColNamesAndValues[i]
			mappedColNamesAndValues.put(listOfColNamesAndValues[i])
		}
		
		/*mappedColNamesAndValues.put('FirstNames', CustomKeywords.'RandomNameGenerator.randomName'())
		mappedColNamesAndValues.put('Surname', CustomKeywords.'RandomNameGenerator.randomSurname'())*/
		//println 'listOfColNamesAndValues is: '+listOfColNamesAndValues
		
	return mappedColNamesAndValues
}


def buildPostApiRequest1(String body) {
		
	RequestObject ro = findTestObject('Object Repository/PolicyCreate/2. SaveSale')
	
	'Create new arrayList'
	ArrayList<TestObjectProperty> HTTPHeader = new ArrayList<TestObjectProperty>()
	
	'Send token in HTTP Header'
	HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, GlobalVariable.bearerKey))
	HTTPHeader.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
	
	ro.setHttpHeaderProperties(HTTPHeader)
	
	ro.setBodyContent(new HttpTextBodyContent(body))
	
	return ro
	
}
