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
import groovy.json.JsonOutput


def counter = [1, 2, 3]
def sheetNames = ['Ifa', 'Child', 'Member', 'Payer', 'Product', 'Rider', 'Spouse', 'Beneficiary', 'Broker']

def nums = ['0'] // , '1'] //, '2']

String jsonString = ''
List<String> jsonList = []
int rowId = 1

nums.each { num ->
	
	sheetNames.each { sheetName ->
	 
		def colNames = findTestData("PolicyCreate/${sheetName}").getColumnNames()

		jsonString += GenerateNewFieldName("${sheetName}","${num}",colNames, rowId)
	}
	
	jsonString = jsonString.substring(0, jsonString.length()-2)
	jsonString = jsonString + ','

}

jsonString = jsonString.substring(0, jsonString.length()-1)
jsonString = ('['+jsonString+']')
println ('The jsonString is: '+jsonString)


def GenerateNewFieldName(String sheetName, String num, String[] columnNames, int rowId) {
	String fieldNames = ""
	
	columnNames.each { colNames ->
		def fieldName = sheetName+"."+num+"."+"${colNames}"
		fieldName = ('{"FieldName": "'+fieldName+'", "Value": "'+findTestData('PolicyCreate/'+"${sheetName}").getValue("${colNames}", rowId)+'"}, ')
		println fieldName
		fieldNames += fieldName
	}	
	
	println fieldNames
	return fieldNames
}

