package com.oliverbelsey.shopgui;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

public final class ShopMenu extends AbstractContainerMenu {
    private static final int SLOTS_ROWS = 6;
    private static final int SLOTS_COLUMNS = 9;
    private static final int SLOTS_COUNT = SLOTS_ROWS * SLOTS_COLUMNS;
    private static final int CONTAINER_START = 0;
    private static final int CONTAINER_END = SLOTS_COUNT;
    private static final int INVENTORY_START = CONTAINER_END;
    private static final int INVENTORY_END = INVENTORY_START + Inventory.INVENTORY_SIZE;

    private static final int CONTAINER_START_X = 8;
    private static final int CONTAINER_START_Y = 18;
    private static final int INVENTORY_START_X = 8;
    private static final int INVENTORY_START_Y = 140;
    private static final int SLOT_SIZE = 18;

    private final Container container;

//    Client initialiser
    public ShopMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(SLOTS_COUNT));
    }

//    Server initialiser
    public ShopMenu(int id, Inventory inventory, Container container) {
        super(ModMenuTypes.SHOP_MENU, id);
        checkContainerSize(container, SLOTS_COUNT);
        this.container = container;

        container.startOpen(inventory.player);

        this.addShopGridSlots();

        this.addStandardInventorySlots(inventory, INVENTORY_START_X, INVENTORY_START_Y);
    }

    private void addShopGridSlots() {
        for (int row = 0; row < SLOTS_ROWS; row++) {
            for (int column = 0; column < SLOTS_COLUMNS; column++) {
                int index = row*SLOTS_COLUMNS+column;
                int x = column*SLOT_SIZE+CONTAINER_START_X;
                int y = row*SLOT_SIZE+CONTAINER_START_Y;
                this.addSlot(new Slot(this.container, index, x, y));
            }
        }
    }
}
