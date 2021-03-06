package businessLogic;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import com.purpleAdmin.qa.pages.WebDirectoryPage;
import com.purpleAdmin.qa.pages.WebHamburgerPage;
import com.purpleAdmin.qa.pages.WebHomePage;
import com.purpleAdmin.qa.pages.WebMapPage;
import com.purpleAdmin.qa.pages.WebEmailPrintPage;
import com.purpleAdmin.qa.util.TestUtil;

public class WebBL {
	WebHomePage homePage = new WebHomePage();
	WebDirectoryPage dirPage = new WebDirectoryPage();
	WebMapPage mapPage = new WebMapPage();
	WebHamburgerPage webHamburgerPage = new WebHamburgerPage();
	WebEmailPrintPage webEmailPrintPage = new WebEmailPrintPage();
	TestUtil testUtil = new TestUtil();
	Boolean isMapDisplayed = false;
	Boolean isBlueDotDisplayed = false;
	Boolean isNavigationStepsDisplayed = false;
	Boolean isDirectionContentClickable = false;
	Boolean isAlertMsgDisplayed = false;
	HashMap<String, String> outputMap = new HashMap<String, String>();
	ExtentTest extentTest = null;
	Boolean blnFlag = false;

	public HashMap<String, String> performOnisteDirections(WebDriver driver, HashMap<String, String> dataMap) {
		Boolean isBlueDotDisplayed = false;
		Boolean isNavigationStepsDisplayed = false;
		Boolean isDirectionContentClickable = false;
		Boolean isIndoorMapDisplayed = false;
		performPrerequisites(driver, dataMap);
		isNavigationStepsDisplayed = dirPage.clickViewOnMap();
		isDirectionContentClickable = dirPage.isDirectionContentClickable();
		isIndoorMapDisplayed = mapPage.verifyOnsiteMapPresence();
		isBlueDotDisplayed = mapPage.verifyBlueDotPresence();
		outputMap.put("DIRECTIONS_AVAILABLE_STATUS", String.valueOf(isNavigationStepsDisplayed));
		outputMap.put("DIRECTIONS_STEPS_CLICKABLE_STATUS", String.valueOf(isDirectionContentClickable));
		outputMap.put("INDOOR_MAP_STATUS", String.valueOf(isIndoorMapDisplayed));
		outputMap.put("BLUEDOT_STATUS", String.valueOf(isBlueDotDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performOnsiteOffsiteDirections(WebDriver driver, HashMap<String, String> dataMap) {
		Boolean isBlueDotDisplayed = false;
		Boolean isNavigationStepsDisplayed = false;
		Boolean isDirectionContentClickable = false;
		Boolean isIndoorMapDisplayed = false;
		performPrerequisites(driver, dataMap);
		dirPage.selectParkingFromMenu();
		isNavigationStepsDisplayed = dirPage.clickViewOnMap();
		isIndoorMapDisplayed = mapPage.verifyOnsiteOffsiteMapPresence();
		isBlueDotDisplayed = mapPage.verifyBlueDotPresence();
		isDirectionContentClickable = dirPage.isDirectionContentClickable();
		outputMap.put("DIRECTIONS_AVAILABLE_STATUS", String.valueOf(isNavigationStepsDisplayed));
		outputMap.put("DIRECTIONS_STEPS_CLICKABLE_STATUS", String.valueOf(isDirectionContentClickable));
		outputMap.put("INDOOR_MAP_STATUS", String.valueOf(isIndoorMapDisplayed));
		outputMap.put("BLUEDOT_STATUS", String.valueOf(isBlueDotDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performOffsiteOnsiteDirections(WebDriver driver, HashMap<String, String> dataMap) {
		Boolean isBlueDotDisplayed = false;
		Boolean isNavigationStepsDisplayed = false;
	//	Boolean isDirectionContentClickable = false;
		Boolean isOffsiteMapDisplayed = false;
		Boolean isgooglePointsABVisible = false;
		performPrerequisites(driver, dataMap);
		dirPage.selectParkingFromMenu();
		dirPage.clickViewOnMap();
		isOffsiteMapDisplayed = mapPage.verifyOffsiteOnsiteMapPresence();
		isgooglePointsABVisible = mapPage.isGooglePointABVisible();
		isNavigationStepsDisplayed = dirPage.clickViewOnMapForOffiste();
		isBlueDotDisplayed = mapPage.verifyBlueDotPresence();
		outputMap.put("DIRECTIONS_AVAILABLE_STATUS", String.valueOf(isNavigationStepsDisplayed));
		outputMap.put("OUTDOOR_MAP_STATUS", String.valueOf(isOffsiteMapDisplayed));
		outputMap.put("GOOGLE_POINTS_STATUS", String.valueOf(isgooglePointsABVisible));
		outputMap.put("BLUEDOT_STATUS", String.valueOf(isBlueDotDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performReverse(WebDriver driver, HashMap<String, String> dataMap)
			throws InterruptedException {
		Boolean isReverseIconEnabled = false;
		driver.get(dataMap.get("URL"));
		acceptTermsDisplayed(dataMap);
		isReverseIconEnabled = dirPage.isReverseIconEnabled();
		outputMap.put("INITIAL_REVERSE_ICON_STATUS", String.valueOf(isReverseIconEnabled));
		homePage.clickStartingPoint();
		dirPage.enterStartingPoint(dataMap.get("STARTING_POINT"));
		Thread.sleep(5000);
		dirPage.ExpandIconPoint();
		dirPage.clickMapIt();
		isReverseIconEnabled = dirPage.isReverseIconEnabled();
		outputMap.put("REVERSE_ICON_WITH_STARTINGPOINT_STATUS", String.valueOf(isReverseIconEnabled));
		dirPage.enterDestinationPoint(dataMap.get("DESTINATION_POINT"));
		dirPage.ExpandIconPoint();
		dirPage.clickMapIt();
		dirPage.clearStartingPointText();
		dirPage.clearDestinationPointText();
		isReverseIconEnabled = dirPage.isReverseIconEnabled();
		outputMap.put("REVERSE_ICON_CLEARROUTE_STATUS", String.valueOf(isReverseIconEnabled));
		return outputMap;
	}

	public HashMap<String, String> performPasteURLInNewTab(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisitesPasteURLInNewTab(driver, dataMap);
		isNavigationStepsDisplayed = dirPage.clickViewOnMap();
		isDirectionContentClickable = dirPage.isDirectionContentClickable();
		isMapDisplayed = mapPage.verifyOnsiteMapPresence();
		isBlueDotDisplayed = mapPage.verifyBlueDotPresence();
		outputMap.put("NAVIGATION_STATUS", String.valueOf(isNavigationStepsDisplayed));
		outputMap.put("NavigationStepsClickable_STATUS", String.valueOf(isNavigationStepsDisplayed));
		outputMap.put("MAP_STATUS", String.valueOf(isMapDisplayed));
		outputMap.put("BLUEDOT_STATUS", String.valueOf(isBlueDotDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performDirectoryBackButton(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisitesDirectoryBackButton(driver, dataMap);
		return outputMap;
	}

	public HashMap<String, String> performDirectoryCrossButton(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisitesDirectoryCrossButton(driver, dataMap);
		return outputMap;
	}

	public HashMap<String, String> performDirectoryInternalSearch(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisitesDirectoryInternalSearch(driver, dataMap);
		isBlueDotDisplayed = mapPage.verifyBlueDotPresence();
		outputMap.put("BLUEDOT_STATUS", String.valueOf(isBlueDotDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performDirectoryMenu(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisitesDirectory(driver, dataMap);
		isNavigationStepsDisplayed = dirPage.clickViewOnMap();
		isMapDisplayed = mapPage.verifyOnsiteMapPresence();
		isBlueDotDisplayed = mapPage.verifyBlueDotPresence();
		outputMap.put("NAVIGATION_STATUS", String.valueOf(isNavigationStepsDisplayed));
		outputMap.put("MAP_STATUS", String.valueOf(isMapDisplayed));
		outputMap.put("BLUEDOT_STATUS", String.valueOf(isBlueDotDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performBlankToField(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisitesBlankToField(driver, dataMap);
		isBlueDotDisplayed = mapPage.verifyBlueDotPresence();
		outputMap.put("BLUEDOT_STATUS", String.valueOf(isBlueDotDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performBlankFromField(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisitesBlankFromField(driver, dataMap);
		isBlueDotDisplayed = mapPage.verifyBlueDotPresence();
		outputMap.put("BLUEDOT_STATUS", String.valueOf(isBlueDotDisplayed));
		return outputMap;
	}

	public HashMap<String, String> EnterSameDirectionsTOFrom(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisites(driver, dataMap);
		isAlertMsgDisplayed = dirPage.CheckSameDirectionsAlertMsg();
		outputMap.put("AlertMsg_STATUS", String.valueOf(isAlertMsgDisplayed));
		return outputMap;
	}

	public HashMap<String, String> EnterOffSiteDirectionsTOFrom(WebDriver driver, HashMap<String, String> dataMap) {
		performPrerequisitesOffSiteToOffSite(driver, dataMap);
		isAlertMsgDisplayed = dirPage.CheckSameDirectionsAlertMsg();
		outputMap.put("AlertMsg_STATUS", String.valueOf(isAlertMsgDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performClearRoute(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isTextCleared = false;
			performPrerequisites(driver, dataMap);
			webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
			webHamburgerPage.clickClearRoute();
			isTextCleared = homePage.isTextCleared();
			System.out.print(isTextCleared);
			outputMap.put("CLEARROUTE_STATUS", String.valueOf(isTextCleared));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	/*
	 * public HashMap<String, String> performAppOverview(WebDriver driver,
	 * HashMap<String, String> dataMap) { try{ Boolean isSkipTutorial = false;
	 * driver.get(dataMap.get("URL")); acceptTermsDisplayed(dataMap);
	 * webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
	 * webHamburgerPage.clickAppOverview(); isSkipTutorial =
	 * homePage.skipTutorial(); System.out.print(isSkipTutorial);
	 * outputMap.put("APPOVERVIEW_STATUS", String.valueOf(isSkipTutorial));
	 * }catch(Exception e){ System.out.println(e); } return outputMap; }
	 */
	public HashMap<String, String> performPrivacyPolicyBtn(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isPPolicyTextLoaded = false;
			Boolean isPPolicyClosed = false;
			driver.get(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
			webHamburgerPage.clickPrivacyPolicy();
			isPPolicyTextLoaded = webHamburgerPage.isPrivacyPolicyLoaded();
			webHamburgerPage.scrolldownPrivacyPolicy();
			isPPolicyClosed = webHamburgerPage.clickClosePPolicy();
			outputMap.put("PRIVACYPOLICY_STATUS", String.valueOf(isPPolicyTextLoaded));
			outputMap.put("PRIVACY_POLICY_CLOSE_STATUS", String.valueOf(isPPolicyClosed));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	public HashMap<String, String> performEULA(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isEULATextLoaded = false;
			Boolean isEULAClosed = false;
			driver.get(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
			webHamburgerPage.clickEULAFromHamburger();
			isEULATextLoaded = webHamburgerPage.isEULALoaded();
			webHamburgerPage.scrolldownEULA();
			isEULAClosed = webHamburgerPage.clickCloseEULAIcon();
			outputMap.put("EULA_STATUS", String.valueOf(isEULATextLoaded));
			outputMap.put("EULACLOSE_STATUS", String.valueOf(isEULAClosed));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	public HashMap<String, String> performMapZoomIn(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isMapZoomedIn, isZoomInClickable = false;
			String actualZoomValue = "";
			performPrerequisites(driver, dataMap);
			isZoomInClickable = mapPage.verifyMapZoomIn();
			actualZoomValue = mapPage.getZoomAttributeValue();
			isMapZoomedIn = actualZoomValue.equals(dataMap.get("EXPECTED_TITLE"));
			System.out.print(isMapZoomedIn);
			outputMap.put("ZOOM_IN_CLICKED_STATUS", String.valueOf(isZoomInClickable));
			outputMap.put("IS_MAP_ZOOMED_IN_STATUS", String.valueOf(isMapZoomedIn));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	public HashMap<String, String> performMapZoomOut(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isMapZoomedOut, isZoomOutClickable = false;
			String actualZoomValue = "";
			performPrerequisites(driver, dataMap);
			isMapZoomedOut = mapPage.verifyMapZoomOut();
			actualZoomValue = mapPage.getZoomAttributeValue();
			isMapZoomedOut = actualZoomValue.equals(dataMap.get("EXPECTED_TITLE"));
			System.out.print(isMapZoomedOut);
			outputMap.put("ZOOM_OUT_CLICKED_STATUS", String.valueOf(isZoomOutClickable));
			outputMap.put("IS_MAP_ZOOMED_OUT_STATUS", String.valueOf(isMapZoomedOut));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	public HashMap<String, String> performMapRecenter(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isMapRecenteredClickable, isMapRecentered = false;
			String actualRecenteredValue = "";
			performPrerequisites(driver, dataMap);
			mapPage.verifyMapRotateLeft();
			isMapRecenteredClickable = mapPage.verifyMapRecenter();
			actualRecenteredValue = mapPage.getRotateAttributeValue();
			isMapRecentered = actualRecenteredValue.equals(dataMap.get("EXPECTED_TITLE"));
			outputMap.put("MAP_RECENTER_CLICK_STATUS", String.valueOf(isMapRecenteredClickable));
			outputMap.put("IS_MAP_RECENTERED_STATUS", String.valueOf(isMapRecentered));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	public HashMap<String, String> performMapRotationLeft(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isRotateLeftBtnClickable, isMapRotatedLeft = false;
			String actualLeftRotationValue = "";
			performPrerequisites(driver, dataMap);
			isRotateLeftBtnClickable = mapPage.verifyMapRotateLeft();
			actualLeftRotationValue = mapPage.getRotateAttributeValue();
			isMapRotatedLeft = actualLeftRotationValue.equals(dataMap.get("EXPECTED_TITLE"));
			outputMap.put("IS_ROTATE_LEFT_BTN_CLICKED_STATUS", String.valueOf(isRotateLeftBtnClickable));
			outputMap.put("IS_MAP_ROTATED_STATUS", String.valueOf(isMapRotatedLeft));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	public HashMap<String, String> performMapRotationRight(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isRotateRightBtnClickable, isMapRotatedRight = false;
			String actualRightRotationValue = "";
			performPrerequisites(driver, dataMap);
			isRotateRightBtnClickable = mapPage.verifyMapRotateRight();
			actualRightRotationValue = mapPage.getRotateAttributeValue();
			isMapRotatedRight = actualRightRotationValue.equals(dataMap.get("EXPECTED_TITLE"));
			outputMap.put("IS_ROTATE_RIGHT_BTN_CLICKED_STATUS", String.valueOf(isRotateRightBtnClickable));
			outputMap.put("IS_MAP_ROTATED_STATUS", String.valueOf(isMapRotatedRight));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	public HashMap<String, String> performKeyLegends(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			Boolean isKeyLegendLoaded, isLegendPresent, isKeyLegendClosed = false;
			performPrerequisites(driver, dataMap);
			isKeyLegendLoaded = mapPage.verifyKeyLegends();
			System.out.print(isKeyLegendLoaded);
			isLegendPresent = mapPage.getKeyLegendsList();
			isKeyLegendClosed = mapPage.closeKeyLegendsModel();
			outputMap.put("KEYLEGENDS_CLICKABLE_STATUS", String.valueOf(isKeyLegendLoaded));
			outputMap.put("KEYLEGENDS_LIST_STATUS", String.valueOf(isLegendPresent));
			outputMap.put("KEYLEGENDS_CLOSE_STATUS", String.valueOf(isKeyLegendClosed));
		} catch (Exception e) {
			System.out.println(e);
		}
		return outputMap;
	}

	public void acceptTermsDisplayed(HashMap<String, String> dataMap) {
		String campusName = dataMap.get("CAMPUS_NAME");
		System.out.println(campusName);
		if (campusName.equals("AMC")) {
			acceptEULATerms(dataMap);

		} else if (campusName.equals("SUMMA")) {
			acceptEULATerms(dataMap); // This method handles EULA popup
			homePage.skipTutorial(); // This method handles Skip Tutorial
		} else {
			homePage.skipTutorial();
		}
	}

	public void acceptEULATerms(HashMap<String, String> dataMap) {
		try {
			homePage.acceptEULAChkBox(dataMap.get("BROWSER_NAME"));
			homePage.acceptEULABtn();
		} catch (Exception e) {
			System.out.print("no EULA popup displayed");
		}
	}

	public void performPrerequisitesPasteURLInNewTab(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			System.out.println(dataMap);
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void performPrerequisitesOffSiteToOffSite(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			System.out.println(dataMap);
			driver.get(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			homePage.clickStartingPoint();
			dirPage.enterStartingPoint(dataMap.get("STARTING_POINT"));
			// Thread.sleep(5000);
			dirPage.selectParkingFromMenu();
			dirPage.enterDestinationPoint(dataMap.get("DESTINATION_POINT"));
			// Thread.sleep(9000);
			dirPage.selectParkingFromMenu();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// public void performPrerequisites(WebDriver driver, HashMap<String,
	// String> dataMap) {
	// try {
	// System.out.println(dataMap);
	// driver.get(dataMap.get("URL"));
	// Thread.sleep(9000);
	// acceptTermsDisplayed(dataMap);
	// verifyCompanyLogo(driver, dataMap);
	// homePage.clickStartingPoint();
	// dirPage.enterStartingPoint(dataMap.get("STARTING_POINT"));
	// Thread.sleep(5000);
	// dirPage.ExpandIconPoint();
	// dirPage.clickMapIt();
	// dirPage.enterDestinationPoint(dataMap.get("DESTINATION_POINT"));
	// // dirPage.selectParkingFromMenu();
	// dirPage.ExpandIconPoint();
	// dirPage.clickMapIt();
	// testUtil.CompareScreenshotElement();
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	// }

//	public void performPrerequisites(WebDriver driver, HashMap<String, String> dataMap) {
//		try {
//			driver.get(dataMap.get("URL"));
//			System.out.println(dataMap);
//			Thread.sleep(5000);
//			acceptTermsDisplayed(dataMap);
//			homePage.clickStartingPoint();
//			dirPage.enterStartingPoint(dataMap.get("STARTING_POINT"));
//			selectLocation(dataMap);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
	
	public void performPrerequisites(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap);
			Thread.sleep(5000);
			acceptTermsDisplayed(dataMap);
			homePage.clickStartingPoint();
			dirPage.enterStartingPoint(dataMap.get("STARTING_POINT"));
			dirPage.ExpandIconPoint();
			dirPage.clickMapIt();
			Thread.sleep(2000);
			dirPage.enterDestinationPoint(dataMap.get("DESTINATION_POINT"));
			if (dataMap.get("DESTINATION_POINT").contains("The cafe17 ")) {
				dirPage.selectOffsiteLocation();
			} else {
				dirPage.ExpandIconPoint();
				dirPage.clickMapIt();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	public void selectLocation(HashMap<String, String> dataMap) {
//		try {
//			if (dataMap.get("STARTING_POINT").contains("the Cafe17 ")) {
//				dirPage.selectOffsiteLocation();
//			} else {
//				dirPage.ExpandIconPoint();
//				dirPage.clickMapIt();
//			}
//			Thread.sleep(1000);
//			dirPage.enterDestinationPoint(dataMap.get("DESTINATION_POINT"));
//			if (dataMap.get("DESTINATION_POINT").contains("The cafe17 ")) {
//				dirPage.selectOffsiteLocation();
//			} else {
//				dirPage.ExpandIconPoint();
//				dirPage.clickMapIt();
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	public void performPrerequisitesDirectoryInternalSearch(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			System.out.println(dataMap);
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			homePage.clickStartingPoint();
			dirPage.clickOnDirectoryMenuTo(dataMap.get("ToDirectoryMenu"));
			dirPage.enterDirectoryInternalSearchInput(dataMap.get("DirectoryInternalInput1"));
			dirPage.clickfirstResultAfterDirectoryInternalSearch();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void performPrerequisitesDirectoryBackButton(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			System.out.println(dataMap);
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			homePage.clickStartingPoint();
			dirPage.clickOnDirectoryMenuTo(dataMap.get("ToDirectoryMenu"));
			dirPage.clickOnDirectoryBackButton(dataMap.get("BROWSER_NAME"));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void performPrerequisitesDirectoryCrossButton(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			System.out.println(dataMap);
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			homePage.clickStartingPoint();
			dirPage.clickOnDirectoryMenuTo(dataMap.get("ToDirectoryMenu"));
			dirPage.clickOnDirectoryCrossButton(dataMap.get("BROWSER_NAME"));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void performPrerequisitesDirectory(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			System.out.println(dataMap);
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			homePage.clickDestinationPoint();
			// System.out.println(dataMap.get("ToDirectoryMenu"));
			// System.out.println(dataMap.get("ToSubMenu1"));
			// System.out.println(dataMap.get("FromDirectoryMenu"));
			// System.out.println(dataMap.get("FromSubMenu1"));
			dirPage.clickOnDirectoryMenuTo(dataMap.get("ToDirectoryMenu"));
			dirPage.clickOnDirectorySubMenuTo(dataMap.get("ToSubMenu1"));
			dirPage.clickOnDirectoryMenuFrom(dataMap.get("FromDirectoryMenu"));
			dirPage.clickOnDirectorySubMenuFrom(dataMap.get("FromSubMenu1"));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void performPrerequisitesBlankFromField(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			System.out.println(dataMap);
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			homePage.clickStartingPoint();
			dirPage.enterStartingPoint(dataMap.get("STARTING_POINT"));
			Thread.sleep(5000);
			dirPage.ExpandIconPoint();
			dirPage.clickMapIt();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void performPrerequisitesBlankToField(WebDriver driver, HashMap<String, String> dataMap) {
		try {
			System.out.println(dataMap);
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap.get("URL"));
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			homePage.clickDestinationPoint();
			dirPage.enterDestinationPoint(dataMap.get("DESTINATION_POINT"));
			Thread.sleep(5000);
			dirPage.ExpandIconPoint();
			dirPage.clickMapIt();
			// testUtil.takeScreenshotElement();
			testUtil.CompareScreenshotElement();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void validateResults(ExtentTest test, HashMap<String, String> outputMap) {
		System.out.println(outputMap);
		for (HashMap.Entry<String, String> entry : outputMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "||||" + value + "||||" + key.replace("_STATUS", "") + "||||" + test);
			new TestUtil().verifyText(value, "true", key.replace("_STATUS", "") + " LOAD VALIDATION", test);
		}
	}

	// Before merge..May be remove
	// public HashMap<String, String> performAppOverview(WebDriver driver,
	// HashMap<String, String> dataMap)
	// throws InterruptedException {
	//
	// Boolean isAppOverViewForwardIconDispalyed = false;
	// Boolean isAppOverViewBackIconDispalyed = false;
	// Boolean isAppOverViewImagesDispalyed = false;
	// driver.get(dataMap.get("URL"));
	// System.out.println(dataMap);
	// acceptTermsDisplayed(dataMap);
	// webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
	// Thread.sleep(5000);
	// webHamburgerPage.clickAppOverview();
	//
	// for (int i = 0; i < 3; i++) {
	// if (TestUtil.waitForElementPresence(webHamburgerPage.SkipTutForwardIcon,
	// driver)) {
	// isAppOverViewForwardIconDispalyed = true;
	// webHamburgerPage.SkipTutForwardIcon.click();
	// } else {
	// System.out.println("Forward icon is not present");
	// }
	//
	// }
	// for (int j = 0; j < 3; j++) {
	// if (TestUtil.waitForElementPresence(webHamburgerPage.SkipTutBackwardIcon,
	// driver)) {
	// isAppOverViewBackIconDispalyed = true;
	// webHamburgerPage.SkipTutBackwardIcon.click();
	// }
	// }
	// if (isAppOverViewForwardIconDispalyed) {
	// isAppOverViewImagesDispalyed =
	// AppOverviewListaWebElement(webHamburgerPage.SkipTutImg1,
	// webHamburgerPage.SkipTutImg2, webHamburgerPage.SkipTutImg3,
	// webHamburgerPage.SkipTutImg4,
	// webHamburgerPage.SkipTutForwardIcon, driver);
	// } else {
	// System.out.println("Forward icon is not present");
	// }
	// outputMap.put("AppOverViewForwardIcon_Status",
	// String.valueOf(isAppOverViewForwardIconDispalyed));
	// outputMap.put("AppOverViewBackIcon_Status",
	// String.valueOf(isAppOverViewBackIconDispalyed));
	// outputMap.put("AppOverImages_Status",
	// String.valueOf(isAppOverViewImagesDispalyed));
	// return outputMap;
	// }

	public HashMap<String, String> performAppOverview(WebDriver driver, HashMap<String, String> dataMap)
			throws InterruptedException {
		Boolean isAppOverViewForwardIconDispalyed = false;
		Boolean isAppOverViewImagesDispalyed = false;
		driver.get(dataMap.get("URL"));
		System.out.println(dataMap);
		Thread.sleep(9000);
		acceptTermsDisplayed(dataMap);
		webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
		Thread.sleep(5000);
		webHamburgerPage.clickAppOverview();
		isAppOverViewForwardIconDispalyed = webHamburgerPage.isAppOverviewForwardIconPresent();
		isAppOverViewImagesDispalyed = webHamburgerPage.isAppOverviewImagePresent();
		outputMap.put("APPOVERVIEW_FORWARD_ICON_STATUS", String.valueOf(isAppOverViewForwardIconDispalyed));
		outputMap.put("APPOVERVIEW_IMAGES_STATUS", String.valueOf(isAppOverViewImagesDispalyed));
		return outputMap;
	}

	// public boolean AppOverviewListaWebElement(WebElement img1, WebElement
	// img2, WebElement img3, WebElement img4,
	// WebElement forwardIcon, WebDriver driver) {
	// int count = 0;
	// List<WebElement> imgList = new ArrayList<WebElement>();
	// imgList.add(webHamburgerPage.SkipTutImg1);
	// imgList.add(webHamburgerPage.SkipTutImg2);
	// imgList.add(webHamburgerPage.SkipTutImg3);
	// imgList.add(webHamburgerPage.SkipTutImg4);
	// for (WebElement img : imgList) {
	// if (TestUtil.waitForElementPresence(img, driver)) {
	// count++;
	// }
	// webHamburgerPage.SkipTutForwardIcon.click();
	// }
	// if (count < 3) {
	// System.out.println("All Skip tutorial images is not present");
	// } else {
	// System.out.println("All Skip tutorial images are present");
	// blnFlag = true;
	// }
	// return blnFlag;
	// }

	public HashMap<String, String> performSubmitFeedback(WebDriver driver, HashMap<String, String> dataMap) {
		String actualPageTitle = "";
		String expectedPageTitle = dataMap.get("EXPECTED_TITLE");
		Boolean isSubmitFeedbackDispalyed = false;
		try {
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap);
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
			webHamburgerPage.clickSubmitFeedback();
			// TODO create method for attribute
			actualPageTitle = driver.getTitle();
			System.out.println(actualPageTitle);
			System.out.println(expectedPageTitle);
			if (actualPageTitle.equals(expectedPageTitle)) {
				System.out.println("Submit feedback link is functional");
				isSubmitFeedbackDispalyed = true;
			} else {
				System.out.println("Submit feedback link is not functional");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputMap.put("SUBMIT_FEEDBACK_STATUS", String.valueOf(isSubmitFeedbackDispalyed));
		return outputMap;
	}

	public HashMap<String, String> performFAQs(WebDriver driver, HashMap<String, String> dataMap) {
		String actualPageTitle = "";
		String expectedPageTitle = dataMap.get("EXPECTED_TITLE");
		Boolean isFAQsDispalyed = false;
		try {
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap);
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
			webHamburgerPage.clickfAQs();
			actualPageTitle = driver.getTitle();
			System.out.println(actualPageTitle);
			System.out.println(expectedPageTitle);
			if (actualPageTitle.equals(expectedPageTitle)) {
				System.out.println("FAQs link is functional");
				isFAQsDispalyed = true;
			} else {
				System.out.println("FAQs link is not functional");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputMap.put("FAQs_STATUS", String.valueOf(isFAQsDispalyed));
		return outputMap;
	}

	public HashMap<String, String> verifyCompanyLogo(WebDriver driver, HashMap<String, String> dataMap)
			throws InterruptedException {
		Boolean isCompanyLogoPresent = false;
		driver.get(dataMap.get("URL"));
		System.out.println(dataMap);
		Thread.sleep(10000);
		acceptTermsDisplayed(dataMap);
		isCompanyLogoPresent = homePage.isLogoPresent();
		outputMap.put("COMPANY_LOGO_STATUS", String.valueOf(isCompanyLogoPresent));
		return outputMap;
	}

	public HashMap<String, String> performEmail(WebDriver driver, HashMap<String, String> dataMap) {
		Boolean isemailSent = false;
		String email = dataMap.get("EMAIL");
		performPrerequisites(driver, dataMap);
		webEmailPrintPage.clickEmailBtn();
		webEmailPrintPage.enterEmail(email);
		webEmailPrintPage.clickSendEmail();
		String actualSentMessage = dataMap.get("EMAILSENTMESSAGE");
		if (actualSentMessage.equals(webEmailPrintPage.getSentMessage()))
			;
		{
			isemailSent = true;
		}
		webEmailPrintPage.closeSendEmail();
		outputMap.put("EMAIL_STATUS", String.valueOf(isemailSent));
		return outputMap;
	}

	public HashMap<String, String> performPrint(WebDriver driver, HashMap<String, String> dataMap) {
		Boolean isPrintLoaded = false;
		performPrerequisites(driver, dataMap);
		webEmailPrintPage.clickPrintBtn();
		isPrintLoaded = webEmailPrintPage.isPrintDirectionVisible();
		outputMap.put("PRINT_STATUS", String.valueOf(isPrintLoaded));
		webEmailPrintPage.clickReturnToMapBtn();

		return outputMap;
	}

	public HashMap<String, String> performEventCalendar(WebDriver driver, HashMap<String, String> dataMap) {
		String actualPageTitle = "";
		String expectedPageTitle = dataMap.get("EXPECTED_TITLE");
		Boolean isEventCalendarDispalyed = false;
		try {
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap);
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
			webHamburgerPage.clickEventCaledar();
			actualPageTitle = driver.getTitle();
			System.out.println(actualPageTitle);
			System.out.println(expectedPageTitle);
			if (actualPageTitle.equals(expectedPageTitle)) {
				System.out.println("Event Calendar link is functional");
				isEventCalendarDispalyed = true;
			} else {
				System.out.println("Event Calendar link is not functional");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputMap.put("EVENT_CALANDER_DISPLAYED_STATUS", String.valueOf(isEventCalendarDispalyed));
		return outputMap;
	}

	public HashMap<String, String> performSendFeedback(WebDriver driver, HashMap<String, String> dataMap) {
		String actualPageTitle = "";
		String expectedPageTitle = dataMap.get("EXPECTED_TITLE");
		Boolean isSendFeedbackdispalyed = false;
		try {
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap);
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
			webHamburgerPage.clickSendFeedback();
			actualPageTitle = driver.getTitle();
			System.out.println(actualPageTitle);
			System.out.println(expectedPageTitle);
			if (actualPageTitle.equals(expectedPageTitle)) {
				System.out.println("Send Feedback link is functional");
				isSendFeedbackdispalyed = true;
			} else {
				System.out.println("Send Feedback link is not functional");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputMap.put("SEND_FEEDBACK_DISPLAYED_STATUS", String.valueOf(isSendFeedbackdispalyed));
		return outputMap;
	}

	public HashMap<String, String> performDownloadMobileApp(WebDriver driver, HashMap<String, String> dataMap) {
		String actualPageTitle = "";
		String expectedPageTitle = dataMap.get("EXPECTED_TITLE");
		Boolean isDownloadMobileAppDisplayed = false;
		try {
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap);
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			webHamburgerPage.clickHamburger(dataMap.get("BROWSER_NAME"));
			webHamburgerPage.clickSendFeedback();
			actualPageTitle = driver.getTitle();
			System.out.println(actualPageTitle);
			System.out.println(expectedPageTitle);
			if (actualPageTitle.equals(expectedPageTitle)) {
				System.out.println("Download Mobile App link is functional");
				isDownloadMobileAppDisplayed = true;
			} else {
				System.out.println("Download Mobile App link is not functional");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputMap.put("DOWNLOAD_MOBILE_APP_STATUS", String.valueOf(isDownloadMobileAppDisplayed));
		return outputMap;
	}

	public HashMap<String, String> performGoogleSearch(WebDriver driver, HashMap<String, String> dataMap) {
		Boolean isGoogleSearchEnable = false;
		try {
			driver.get(dataMap.get("URL"));
			System.out.println(dataMap);
			Thread.sleep(9000);
			acceptTermsDisplayed(dataMap);
			homePage.clickStartingPoint();
			dirPage.enterStartingPoint(dataMap.get("STARTING_POINT"));
			isGoogleSearchEnable = dirPage.isGoogleSearchEnable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputMap.put("GOOGLE_SEARCH_STATUS", String.valueOf(isGoogleSearchEnable));
		return outputMap;
	}

}
