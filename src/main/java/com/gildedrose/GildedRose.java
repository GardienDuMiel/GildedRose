package com.gildedrose;

class GildedRose {

  private static final String BRIE = "Aged Brie";
  private static final String PASS = "Backstage passes to a TAFKAL80ETC concert";
  private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (int i = 0; i < items.length; i++) {
      if (!items[i].name.equals(BRIE)
          && !items[i].name.equals(PASS)) {
        if (items[i].quality > 0) {
          if (!items[i].name.equals(SULFURAS)) {
            items[i].quality = items[i].quality - 1;
          }
        }
      } else {
        if (items[i].quality < 50) {
          items[i].quality = items[i].quality + 1;

          if (items[i].name.equals(PASS)) {
            if (items[i].sellIn < 11) {
              if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
              }
            }

            if (items[i].sellIn < 6) {
              if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
              }
            }
          }
        }
      }

      if (!items[i].name.equals(SULFURAS)) {
        items[i].sellIn = items[i].sellIn - 1;
      }

      if (items[i].sellIn < 0) {
        if (!items[i].name.equals(BRIE)) {
          if (!items[i].name.equals(PASS)) {
            if (items[i].quality > 0) {
              if (!items[i].name.equals(SULFURAS)) {
                items[i].quality = items[i].quality - 1;
              }
            }
          } else {
            items[i].quality = 0;
          }
        } else {
          if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;
          }
        }
      }
    }
  }
}
