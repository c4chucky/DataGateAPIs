package mypackage
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.time.LocalDateTime
import java.util.Date
import java.text.SimpleDateFormat;
import com.kms.katalon.core.configuration.RunConfiguration

@Keyword
def moveDataFileToProcessed() {

	Date date = new Date()
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss")

	String projectDir = RunConfiguration.getProjectDir()

	Path source = Paths.get(projectDir + "/Documents/SaveSale.xlsx");
	Path target = Paths.get(projectDir + "/Documents/Processed/SaveSale" + sdf.format(date) + ".xlsx");

	Files.move(source, target, REPLACE_EXISTING);
}
