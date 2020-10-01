package tiki;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.junit.Assert;
//import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class TestTikiWeb {
	@Managed(driver = "firefox")
	WebDriver objDriver;
	
	@Test
	public void TC_01_Open_Tiki_Web() {
		
		String url = "https://tiki.vn/";
		objDriver.get(url);
		objDriver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_02_Search_Product () {
		
		String valueCondition = "apple";
		String xPathBtnTimKiem = "//*[contains(text(), 'Tìm kiếm')]//parent::button[1]";
		String xPathTextTimKiem = "//*[contains(text(), 'Tìm kiếm')]/..//preceding-sibling::input[1]";
		
		WebElement eleTextTimKiem = findElementXPathConditionExist(xPathTextTimKiem, objDriver);
		eleTextTimKiem.sendKeys(valueCondition);
		
		WebElement eleBtnTimKiem = findElementXPathConditionExist(xPathBtnTimKiem, objDriver);
		Actions act = new Actions(objDriver);
		act.moveToElement(eleBtnTimKiem).click().build().perform();
		
	}
	
	@Test
	public void TC_03_Click_CheckBox_TikiNow () {
		
		boolean loadingPageStatus = isLoadingPage(objDriver);
		
		if (loadingPageStatus == true) {
			
			String xPathCheckBoxTikiNow = "//div[@class='check-2h-supported']/label[2]/input[1]";
			WebElement eleCheckBoxTikiNow = findElementXPathConditionExist(xPathCheckBoxTikiNow, objDriver);
			Actions act = new Actions(objDriver);
			act.moveToElement(eleCheckBoxTikiNow).click().build().perform();
			
			//sau khi click checkbox thi chon dia chi Nhan hang truoc
			
			
			
		} else {
			
			System.out.println("loadingPageStatus = " + loadingPageStatus);
			
		}
				
		
	}
	
	
	@Test
	public void TC_04_Choice_Product_Five() {
		
		String xPathManyProducts = "//div[@class='product-box-list']/div[contains(@data-score, '')]";
		List<WebElement> lstEleProduct = findElementsXPathConditionExist(xPathManyProducts, objDriver);
		WebElement eleProductFive = lstEleProduct.get(4);
		
		Actions act = new Actions(objDriver);
		act.moveToElement(eleProductFive).click().build().perform();
		
	}
	
	@Test
	public void TC_05_Check_Detail_Product() {
		
		String titleProductExpected = "Đồng Hồ Thông Minh Apple Watch Series 3 GPS Aluminum Case With Sport Band - Nhập Khẩu Chính Hãng";
		String xPathTitleProduct = "//h1[@class='title']";
		String xPathImageProduct = "//div[@class='group-images']//img[1]";
		String xPathPrice = "//div[@class='left']//following-sibling::div[@class='group']/div[1]/p[1]";
		
		WebElement eleTitleProduct;
		WebElement eleImageProduct;
		WebElement elePrice;
		
		eleTitleProduct = findElementXPathConditionExist(xPathTitleProduct, objDriver);

		ArrayList<Boolean> arrCheckStatusChoiceProduct = new ArrayList<>();
		
		if (eleTitleProduct !=null) {
			
			//check title cua san pham dang muon chon
			String titleProductActual = eleTitleProduct.getText().trim();
			if (titleProductActual.equals(titleProductExpected) ) {
				
				arrCheckStatusChoiceProduct.add(true); 
				
			} else {
				
				arrCheckStatusChoiceProduct.add(false);
				
			}
			
			//check Image cua san pham dang muon chon
			eleImageProduct = findElementXPathConditionExist(xPathImageProduct, objDriver);
			if (eleImageProduct !=null) {
				
				  if (eleImageProduct.isDisplayed()) {
					   
					  arrCheckStatusChoiceProduct.add(true);
					  
				  } else {
					  
					  arrCheckStatusChoiceProduct.add(false);
					  
				  }
				
			} else {
				
				arrCheckStatusChoiceProduct.add(false);
				
			}
			
			//check Price cua san pham dang muon chon
			elePrice = findElementXPathConditionExist(xPathPrice, objDriver);
			if (elePrice !=null) {
				
				if (elePrice.isDisplayed()) {
					
					arrCheckStatusChoiceProduct.add(true);
					
				} else {
					
					arrCheckStatusChoiceProduct.add(false);
					
				}
				
			} else {
				
				arrCheckStatusChoiceProduct.add(false);
				
			}
			
		} else {
			
			 arrCheckStatusChoiceProduct.add(false);
			
		}
		
		
		String xPathPlusButton = "//button[contains(text(),'+')]";
		String xPathChonMuaButton = "//button[contains(text(), 'Chọn mua')]";
		String xPathXemGioHangVaThanhToanButton = "//a[contains(text(),'Xem giỏ hàng và thanh toán')]";
		
	    if (!arrCheckStatusChoiceProduct.contains(false)) {
	    	
	    	   WebElement elePlusButton = findElementXPathConditionExist(xPathPlusButton, objDriver);
	    	   Actions act = new Actions(objDriver);
	    	   
	    	   //click 1 lan vao nut PlusButton de tang so luong len 2 
//	    	   act.moveToElement(elePlusButton).click().build().perform();
	    	   
	    	   WebElement eleChonMuaButton = findElementXPathConditionExist(xPathChonMuaButton, objDriver);
	    	   act.moveToElement(eleChonMuaButton).click().build().perform();
	    	   
	    	   //click Xem gio hang va thanh thoan button de vao gio hang xem thong tin va thanh tien dung chua? 
	    	   WebElement eleXemGioHangVaThanhToanButton = findElementXPathConditionExist(xPathXemGioHangVaThanhToanButton, objDriver);
	    	   act.moveToElement(eleXemGioHangVaThanhToanButton).click().build().perform();
	    	   
	    	   String xPathTitleProductGH = "//div[@class='cart-products__desc']/a[1]";
	    	   String xPathPriceProductGH ="//p[@class='cart-products__real-prices']";
	    	   String xPathThanhTienGH = "//span[@class='prices__value prices__value--final']";
	    	   
	    	   WebElement eleTitleProductGH = findElementXPathConditionExist(xPathTitleProductGH, objDriver);
	    	   WebElement elePriceProductGH = findElementXPathConditionExist(xPathPriceProductGH, objDriver);
	    	   WebElement eleThanhTienGH = findElementXPathConditionExist(xPathThanhTienGH, objDriver);
	    	   
	    	   boolean isCheckDisplayProductGH = false;
	    	   
	    	   if (eleTitleProductGH !=null 
	    			 && elePriceProductGH !=null 
	    			 && eleThanhTienGH !=null) {
	    		   
	    		      if (eleTitleProductGH.isDisplayed()
	    		    	  && elePriceProductGH.isDisplayed()
	    		    	  && eleThanhTienGH.isDisplayed()) {
	    		    	  
	    		    	  isCheckDisplayProductGH = true;
	    		    	  
	    		      } else {
	    		    	  
	    		    	  isCheckDisplayProductGH = false;
	    		    	  
	    		      }
	    		         
	    		   
	    	   } else {
	    		   
	    		   isCheckDisplayProductGH = false;
	    		   
	    	   }
	    	   
	    	   Assert.assertTrue(isCheckDisplayProductGH);
	    	   
	    	
	    } else {
	    	
	    	System.out.println("Stop TC_05_Check_Detail_Product");
	    	
	    }
		
	}
	
    private WebElement findElementXPathConditionExist(String xPathName,
    												 WebDriver driver) {	 
		 
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_WAIT_FIND_ELEMENT_EXIST_DOM);
			 By ByElement = getByElementXPath(xPathName); 
			 // check this element exist on DOM 
			 WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ByElement));	
			 
			 return element;
		 } catch (TimeoutException ex) {
			 return null;
		 }	 
	 }
    
    private List<WebElement> findElementsXPathConditionExist(String xPathName, 
    													    WebDriver driver) {	
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_WAIT_FIND_ELEMENT_EXIST_DOM);
			 By ByElement = getByElementXPath(xPathName);
			 		
			 // check this element exist on DOM 
			 WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ByElement));
			 List<WebElement> selectResultsAsListCollection = element.findElements(ByElement);
			 
			 return selectResultsAsListCollection;
		 } catch (Exception ex) {
			 return null; 
		 }
		 
	 }
    
    private By getByElementXPath (String xPathName){
		 By byXPathName = By.xpath(xPathName);
		 return byXPathName;
	 }
    
    private boolean isLoadingPage (WebDriver driver) {
    	
    	boolean check = false;
    	JavascriptExecutor js = (JavascriptExecutor) driver; 
    	
    	while (true) {
    		
    		check = ((String) js.executeScript("return document.readyState")).equals("complete");
    		
    		if (check) {
    		    
    			break;
    			
    		}	
    		
    	}
    	
    	return check;
    }
	
}