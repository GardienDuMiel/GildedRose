package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    @DisplayName("Test that the name is unchanged")
    void testName() {
        Item element = new Item("foo", 0, 0);
        GildedRose app = new GildedRose(new Item[] {element});
        app.updateQuality();
        assertEquals("foo", element.name, "the name changed");
    }

    @Test
    @DisplayName("Test that quality degrades twice as fast after sellIn date")
    void testQualityDegradesTwiceAsFastAfterSellInDate() {
        Item element = new Item("Elixir of the Mongoose", 0, 10); // SellIn date has passed
        GildedRose app = new GildedRose(new Item[] {element});
        app.updateQuality();
        int initialQuality = 10;
        int expectedQuality = initialQuality - 2; // Quality should decrease by 2 after sellIn date
        assertEquals(expectedQuality, element.quality, "Quality did not degrade twice as fast");
    }

    @Test
    @DisplayName("Test that quality is never negative")
    void testQualityNeverNegative() {
        Item element = new Item("Elixir of the Mongoose", 5, 0); // Quality initially set to 0
        GildedRose app = new GildedRose(new Item[] {element});
        app.updateQuality();
        int updatedQuality = element.quality;
        assertTrue(updatedQuality >= 0, "Quality became negative");
    }

    @Test
    @DisplayName("Test that Aged Brie increases in quality over time")
    void testAgedBrieQualityIncreasesOverTime() {
        Item agedBrie = new Item("Aged Brie", 10, 20); // Aged Brie with positive quality and sellIn
        GildedRose app = new GildedRose(new Item[] {agedBrie});
        app.updateQuality();
        int initialQuality = 20;
        int expectedQuality = initialQuality + 1; // Quality should increase by 1 over time
        assertEquals(expectedQuality, agedBrie.quality, "Aged Brie quality did not increase");
    }

    @Test
    @DisplayName("Test that quality is never more than 50")
    void testQualityNeverMoreThan50() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49); // Quality initially set to 49, which is less than 50
        GildedRose app = new GildedRose(new Item[] {item});
        app.updateQuality();
        assertTrue(item.quality <= 50, "Quality exceeded 50");
    }

    @Test
    @DisplayName("Test that quality is never more than 50")
    void testQualityNeverMoreThan50_2() {
        Item item = new Item("Aged Brie", 5, 50); // Quality initially set to 50
        GildedRose app = new GildedRose(new Item[] {item});
        app.updateQuality();
        assertTrue(item.quality <= 50, "Quality exceeded 50");
    }

    @Test
    @DisplayName("Test that Sulfuras, being legendary, never loses quality")
    void testSulfurasNeverLosesQuality() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 10, 80); // Legendary item with quality 80
        GildedRose app = new GildedRose(new Item[] {sulfuras});
        app.updateQuality();
        int initialQuality = 80;
        int expectedQuality = initialQuality; // Quality should remain unchanged
        assertEquals(expectedQuality, sulfuras.quality, "Sulfuras quality changed");
    }

    @Test
    @DisplayName("Test Backstage Passes quality increase over time")
    void testBackstagePassesQualityIncreaseOverTime() {
        testQualityIncreaseBy2WithSellInBetween6And10();
        testQualityIncreaseBy3WithSellInBetween1And5();
        testQualityDropsToZeroAfterConcert();
    }

    private void testQualityIncreaseBy2WithSellInBetween6And10() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        GildedRose app = new GildedRose(new Item[] {backstagePasses});
        app.updateQuality();
        assertEquals(22, backstagePasses.quality);
    }

    private void testQualityIncreaseBy3WithSellInBetween1And5() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        GildedRose app = new GildedRose(new Item[] {backstagePasses});
        app.updateQuality();
        assertEquals(23, backstagePasses.quality);
    }

    private void testQualityDropsToZeroAfterConcert() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        GildedRose app = new GildedRose(new Item[] {backstagePasses});
        app.updateQuality();
        assertEquals(0, backstagePasses.quality);
    }

}

