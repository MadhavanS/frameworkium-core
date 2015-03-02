package com.frameworkium.tests.web;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Issue;

import com.frameworkium.pages.web.BasicAuthSuccessPage;
import com.frameworkium.pages.web.CheckboxesPage;
import com.frameworkium.pages.web.FileDownloadPage;
import com.frameworkium.pages.web.DragAndDropPage;
import com.frameworkium.pages.web.DropdownPage;
import com.frameworkium.pages.web.DynamicLoadingExamplePage;
import com.frameworkium.pages.web.FileUploadPage;
import com.frameworkium.pages.web.FileUploadSuccessPage;
import com.frameworkium.pages.web.FormAuthenticationPage;
import com.frameworkium.pages.web.FormAuthenticationSuccessPage;
import com.frameworkium.pages.web.HoversPage;
import com.frameworkium.pages.web.JQueryUIPage;
import com.frameworkium.pages.web.JQueryUIMenuPage;
import com.frameworkium.pages.web.JavaScriptAlertsPage;
import com.frameworkium.pages.web.KeyPressesPage;
import com.frameworkium.pages.web.SecureFileDownloadPage;
import com.frameworkium.pages.web.WelcomePage;
import com.frameworkium.tests.internal.BaseTest;

public class TheInternetHerokuWebTest extends BaseTest {

	@Issue("KT-1")
	@Test(description = "Basic Auth")

	public final void basicAuth() {
		// Navigate to the dynamic loading hidden element page
		BasicAuthSuccessPage basicAuthSuccess = WelcomePage.open().then().clickBasicAuth("admin", "admin");

		//Confirm that the returned page has the text present
		assertThat(basicAuthSuccess.getSource()).contains("Congratulations! You must have the proper credentials.");
	}

	@Issue("KT-1")
	@Test(description = "Checkboxes")
	public final void checkBoxes() {
		
		// Navigate to the checkboxes page
		CheckboxesPage checkboxesPage = WelcomePage.open().then().clickCheckboxesLink();

		// Set both checkboxes to checked
		checkboxesPage.checkAllCheckboxes();
		
		// Assert that both checkboxes are checked
		assertThat(checkboxesPage.getCheckednessOfCheckboxes()).named("checkedness of checkboxes").doesNotContain(false);
	}

	@Ignore
	@Issue("KT-1")
	@Test(description = "Drag and Drop")
	public final void dragAndDrop() {
		
		// Navigate to the checkboxes page
		DragAndDropPage dragAndDropPage = WelcomePage.open().then().clickDragAndDropLink();

		//Drag A onto B
		dragAndDropPage.dragAontoB();

		//Assert on the order?
	}


	@Issue("KT-1")
	@Test(description = "Dropdown")
	public final void dropdown() {
		
		// Navigate to the checkboxes page
		DropdownPage dropdownPage = WelcomePage.open().then().clickDropdownLink();
	
		//Drag A onto B
		dropdownPage.selectFromDropdown("Option 1");
	
		//Assert selected
		assertThat(dropdownPage.getSelectedOptionText()).named("selected option in dropdown").isEqualTo("Option 1");
	}
	
	@Issue("KT-1")
	@Test(description = "Dynamic loading")
	public final void dynamicLoading() {
		// Navigate to the dynamic loading hidden element page
		DynamicLoadingExamplePage dynamicLoadingExamplePage = WelcomePage.open().then().clickDynamicLoading()
				.then().clickExample1();

		// Assert that the element is hidden
		assertThat(dynamicLoadingExamplePage.isElementDisplayed()).named("element visibility").isFalse();

		// Click start and wait for element to be displayed
		dynamicLoadingExamplePage.clickStart().then().waitForElementToBeDisplayed();

		// Assert that the element is indeed displayed
		assertThat(dynamicLoadingExamplePage.isElementDisplayed()).named("element visibility").isTrue();
	}
	
	@Issue("KT-1")
	@Test(description = "File Download")
	public final void fileDownload() {
		
		// Navigate to the download page
		FileDownloadPage downloadPage = WelcomePage.open().then().clickFileDownloadLink();
		
		//If you have the file
		//File testFile = new File("/Users/robgates55/avatar.jpg");
		//FileInputStream f = new FileInputStream(testFile.getAbsolutePath());
		//int size = IOUtils.toByteArray(f).length;
		//IOUtils.closeQuietly(f);
		
		//If you know the size to expect
		int size = 7996;
		
		// Confirm size of the downloaded file is as expected
		assertThat(downloadPage.getSizeOfFirstFile()).isEqualTo(size);
		
	}
	
	@Issue("KT-1")
	@Test(description = "File Upload")
	public final void fileUpload() {
		
		// Navigate to the upload page
		FileUploadPage fileUploadPage = WelcomePage.open().then().clickFileUploadLink();
		
		//Pick a local file we're going to upload
		File fileToUpload = new File("/Users/robgates55/textfile.txt");
		
		//Upload the file and confirm we land on the success page
		FileUploadSuccessPage successPage = fileUploadPage.uploadFile(fileToUpload);
	
		//Confirm that the uploaded files list contains our filename
		assertThat(successPage.getUploadedFiles()).contains(fileToUpload.getName());
		
	}
	
	@Issue("KT-1")
	@Test(description = "Form Authentication")
	public final void formAuthentication() {
		
		// Navigate to the form authentication page
		FormAuthenticationPage formAuthenticationPage = WelcomePage.open().then().clickFormAuthenticationLink();
	
		//Log in with the username password provided
		FormAuthenticationSuccessPage successPage = formAuthenticationPage.validLogin("tomsmith", "SuperSecretPassword!");
	
		//Confirm that we're on the success page
		assertThat(successPage.getSource()).contains("Welcome to the Secure Area");
		
	}

	@Issue("KT-1")
	@Test(description = "Hovers")
	public final void hovers() {
		
		// Navigate to the hovers page
		HoversPage hoversPage = WelcomePage.open().then().clickHoversLink();
	
		assertThat(hoversPage.getFirstFigureCaption()).contains("name: user1");
			
	}
	
	@Issue("KT-1")
	@Test(description = "JQuery UI")
	public final void jqueryUI() {
		
		// Navigate to the jquery UI page
		JQueryUIMenuPage jqueryUIMenuPage = WelcomePage.open().then().clickJQueryUILink();
	
		//Browse to the ui page
		JQueryUIPage UIPage = jqueryUIMenuPage.clickBackToUI();
		
		//Click the menu link to return to the menu page
		jqueryUIMenuPage = UIPage.clickMenuLink();
			
		//Check that the excel file link matches the string
		assertThat(jqueryUIMenuPage.getExcelFileURLAsString()).isEqualTo("http://the-internet.herokuapp.com/download/jqueryui/menu/menu.xls");
	}
	
	@Issue("KT-1")
	@Test(description = "Javascript Alerts")
	public final void javascriptAlerts() {
		
		// Navigate to the javascript alerts page
		JavaScriptAlertsPage javascriptAlerts = WelcomePage.open().then().clickjavascriptAlertsLink();
	
		javascriptAlerts.clickAlertButtonAndAccept();
		assertThat(javascriptAlerts.getResultText()).isEqualTo("You successfuly clicked an alert");
		
		javascriptAlerts.clickAlertButtonAndDismiss();
		assertThat(javascriptAlerts.getResultText()).isEqualTo("You successfuly clicked an alert");
		
		javascriptAlerts.clickConfirmButtonAndAccept();
		assertThat(javascriptAlerts.getResultText()).isEqualTo("You clicked: Ok");
		
		javascriptAlerts.clickConfirmButtonAndDismiss();
		assertThat(javascriptAlerts.getResultText()).isEqualTo("You clicked: Cancel");

		javascriptAlerts.clickPromptButtonAndEnterPrompt("Blah blah blah");
		assertThat(javascriptAlerts.getResultText()).isEqualTo("You entered: Blah blah blah");

	}
	
	@Issue("KT-1")
	@Test(description = "Key Presses")
	public final void keypresses() {
		
		// Navigate to the key presses page
		KeyPressesPage keyPressesPage = WelcomePage.open().then().clickKeyPressesLink();
	
		keyPressesPage.enterKeyPress(Keys.ENTER);
		
		assertThat(keyPressesPage.getResultText()).isEqualTo("You entered: " + Keys.ENTER.name());
	}

	@Issue("KT-1")
	@Test(description = "Secure file Download")
	public final void secureFileDownload() {
		
		// Navigate to the secure file downloads page
		SecureFileDownloadPage secureFileDownloadPage = WelcomePage.open().then().clickSecureFileDownloadsLink("admin","admin");

		//Assert that the page contains the text
		assertThat(secureFileDownloadPage.getSource()).contains("Secure File Downloader");
		
	}

}
