package crazypants.enderio.machine.killera;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.enderio.core.client.gui.widget.GhostBackgroundItemSlot;
import com.enderio.core.client.gui.widget.GhostSlot;

import crazypants.enderio.item.darksteel.DarkSteelItems;
import crazypants.enderio.machine.gui.AbstractMachineContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerKillerJoe extends AbstractMachineContainer<TileKillerJoe> {

  static private final Item[] slotItems = { Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD, Items.GOLDEN_SWORD,
      Items.DIAMOND_SWORD, DarkSteelItems.itemDarkSteelSword };
  static private final Random rand = new Random();

  public ContainerKillerJoe(InventoryPlayer playerInv, TileKillerJoe te) {
    super(playerInv, te);
  }

  @Override
  protected void addMachineSlots(InventoryPlayer playerInv) {
    addSlotToContainer(new Slot(getInv(), 0, 80, 25) {
      @Override
      public boolean isItemValid(@Nullable ItemStack itemStack) {
        return getInv().isItemValidForSlot(0, itemStack);
      }
    });
  }

  public void createGhostSlots(List<GhostSlot> slots) {
    slots.add(new GhostBackgroundItemSlot(slotItems[rand.nextInt(slotItems.length)], 80, 25));
  }

}
