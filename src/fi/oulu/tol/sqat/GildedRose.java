package fi.oulu.tol.sqat;

import java.util.ArrayList;
import java.util.List;


public class GildedRose {

	private static List<Item> items = null;

	/**
	 * @param args
	 
	public static void main(String[] args) {
		
        System.out.println("OMGHAI!");
		
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        updateQuality();
}
	*/
    public static void updateQuality()
    {
        for (int i = 0; i < items.size(); i++)
        {
            // QUALITY PÄIVITYS: EI JUUSTO, EI BACKSTAGE PASS, EI SULFURAS
        	if (!"Aged Brie".equals(items.get(i).getName()) && !"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName()))
            {
        		// QUALITY SUUREMPI KUIN NOLLA
                if (items.get(i).getQuality() > 0)
                {
                	// EI SULFURAS
                    if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).getName()))
                    {
                        items.get(i).setQuality(items.get(i).getQuality() - 1);
                    }
                }
                
            }
        	// QUALITY PÄIVITYS: JUUSTO, BACKSTAGE PASS
            else 
            {
                // QUALITY ALLE 50, VOI KASVATTAA
            	if (items.get(i).getQuality() < 50)
                {         
                    
                    // SELL-IN 5 PÄIVÄSSÄ
                    if (items.get(i).getSellIn() < 6)
                    {
                    	items.get(i).setQuality(items.get(i).getQuality() + 3);                        
                    }
                    // SELL-IN 10 PÄIVÄSSÄ
                    else if (items.get(i).getSellIn() < 11)
                    {
                    	// JOS QUALITY < 50, JUUSTO TAI BACKSTAGE PASS, KASVATA QUALITY +3
                        items.get(i).setQuality(items.get(i).getQuality() + 2);                        
                    }
                    else {
                    	items.get(i).setQuality(items.get(i).getQuality() + 1);
                    }
                    
                }
            }
        	// SELL-IN PÄIVITYS, KAIKKI PAITSI SULFURAS
            if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).getName()))
            {            	
                items.get(i).setSellIn(items.get(i).getSellIn() - 1);
            }
            
            // SELL-IN PIENEMPI KUIN 0
            if (items.get(i).getSellIn() < 0)
            {
                // EI JUUSTO
            	if (!"Aged Brie".equals(items.get(i).getName()))
                {
                    // EI BACKSTAGE PASS
            		if (!"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName()))
                    {
                        // QUALITY SUUREMPI KUIN 0
            			if (items.get(i).getQuality() > 0)
                        {
            				// EI SULFURAS
                            if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).getName()))
                            {
                            	// ALENNA KAIKKIEN PAITSI JUUSTO, BACKSTAGE PASS, SULFURAS ARVOA -2
                                items.get(i).setQuality(items.get(i).getQuality() - 2);
                            }
                            
                        }
                    }
                    else
                    {
                    	// NOLLAA BACKSTAGE PASS QUALITY
                        items.get(i).setQuality(items.get(i).getQuality() - items.get(i).getQuality());
                    }
                }            	
            	
            }
            
        } // for
    }
    
    //constructor
    public GildedRose() {
    	items = new ArrayList<Item>();
    }
    
    //getter
    public List<Item> getItems() {
    	return items;
    }
    //setter
    public void setItem(Item item) {
    	items.add(item);
    }
    
    //update one day
    public void oneDay() {
    	updateQuality();
    }

}
