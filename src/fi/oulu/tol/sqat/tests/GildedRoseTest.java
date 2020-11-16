package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.List;
import org.junit.Test;
import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testDexterityVestQuality_oneDay() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	@Test
	public void testDexterityVestQuality_twoDays() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		
		inn.oneDay();
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 18, quality);
	}
	
	@Test
	public void testDexterityVest_QualityGoesNegative_threeDays() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 2));
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();		
		
		assertEquals("Failed quality for Dexterity Vest", 0, quality);
	}
	
	@Test
	public void testDexterityVestSellin_oneDay() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 2, 20));		
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		inn.oneDay();
		int sellin = items.get(0).getSellIn();		
		
		//sellin value 1
		assertEquals("Failed sellIn for Dexterity Vest", 1, sellin);
	}
	
	@Test
	public void testDexterityVestQuality_SellInExpiresOneDay() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 2, 20));		
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();			
		
		inn.oneDay();		
		inn.oneDay();		
		inn.oneDay();		
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Dexterity Vest", 15, quality);
	}
	
	@Test
	public void testDexterityVestQuality_SellInGoesNegative_fiveDays() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 3, 4));		
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();			
		
		inn.oneDay();		
		inn.oneDay();		
		inn.oneDay();
		inn.oneDay();		
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Dexterity Vest", 0, quality);
	}
	
	@Test
	public void test_BrieQuality_sellIn2_fourDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Aged Brie", 2, 0));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();		
		inn.oneDay();		
		inn.oneDay();		
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed Brie quality", 12, quality);
	}
	
	@Test
	public void test_BrieQuality_sellIn5_threeDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Aged Brie", 5, 0));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();	
		
		int quality = items.get(0).getQuality();
		assertEquals("Failed Brie quality", 9, quality);
	}
	
	@Test
	public void test_BrieQuality_sellIn12_threeDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Aged Brie", 12, 0));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed Brie quality", 4, quality);
	}
	
	@Test
	public void test_BrieMaxQuality_sellIn12_threeDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Aged Brie", 12, 48));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed Brie quality", 50, quality);
	}
	
	@Test
	public void test_BrieNegativeStartQuality_50Days_sellIn50() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Aged Brie", 50, -40));		
		List<Item> items = inn.getItems();		
		
		for (int i = 0; i < 50; i++) {
			inn.oneDay();
		}		
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed Brie quality", 25, quality);
	}
	
	@Test
	public void test_BrieQuality_VeryOldBrie_sellIn60_100Days() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Aged Brie", 50, -130));		
		List<Item> items = inn.getItems();		
		
		
		for (int i = 0; i < 100; i++) {			
			//System.out.format("%s %d \n", items.get(0).getName() + ", day: ", i+1);
			inn.oneDay();
			//System.out.println(items.get(0).getQuality());
		}		
		
		int quality = items.get(0).getQuality();
		
		// should be 50, is actually 52
		assertEquals("Failed Brie quality", 50, quality);
	}
	
	
	@Test
	public void test_BSPassQuality_sellIn12_threeDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 12, 28));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed BS-Pass quality", 32, quality);
	}
	
	@Test
	public void test_BSPassQuality_sellIn7_fourDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 7, 28));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed BS-Pass quality", 38, quality);
	}
	
	@Test
	public void test_BSPassQuality_threeDays_sellIn2_passExpires() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 2, 28));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed BS-Pass quality", 0, quality);
	}
	
	@Test
	public void test_BSPassMaxQuality_sellIn15_threeDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 48));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed BS-Pass quality", 50, quality);
	}
	
	@Test
	public void test_BSPassNegativeStartQuality_50Days_sellIn50() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 50, -40));		
		List<Item> items = inn.getItems();		
		
		for (int i = 0; i < 50; i++) {
			inn.oneDay();
		}		
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed BS-Pass quality", 25, quality);
	}
	
	@Test
	public void test_SulfurasQuality_sellIn15_threeDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 15, 45));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed Sulfuras quality", 45, quality);
	}
	
	@Test
	public void test_SulfurasNegativeStartQuality_sellIn50_threeDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 50, -40));		
		List<Item> items = inn.getItems();		
		
		for (int i = 0; i < 50; i++) {
			inn.oneDay();
		}		
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed Sulfuras quality", -40, quality);
	}
	
	@Test
	public void test_SulfurasSellIn_PositiveQuality_NegativeSellIn_threeDays() {
		
		GildedRose inn = new GildedRose();		
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -50, 40));		
		List<Item> items = inn.getItems();		
		
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();		
		
		int sellin = items.get(0).getSellIn();
		
		assertEquals("Failed Sulfuras sellin", -50, sellin);
	}
}
