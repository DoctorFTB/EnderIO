package crazypants.enderio.machine.monitor.v2;


import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.enderio.core.api.client.gui.IAdvancedTooltipProvider;
import com.enderio.core.client.handlers.SpecialTooltipHandler;

import crazypants.enderio.ModObject;
import crazypants.enderio.machine.AbstractMachineBlock;
import crazypants.enderio.network.PacketHandler;
import crazypants.enderio.render.IBlockStateWrapper;

public class BlockPMon extends AbstractMachineBlock<TilePMon> implements IAdvancedTooltipProvider {

  public static BlockPMon blockPMon;

  public static BlockPMon create() {
    PacketHandler.INSTANCE.registerMessage(PacketPMon.ClientHandler.class, PacketPMon.class, PacketHandler.nextID(), Side.CLIENT);
    PacketHandler.INSTANCE.registerMessage(PacketPMon.ServerHandler.class, PacketPMon.class, PacketHandler.nextID(), Side.SERVER);
    blockPMon = new BlockPMon();
    blockPMon.init();
    return blockPMon;
  }

  protected BlockPMon() {
    super(ModObject.blockPowerMonitorv2, TilePMon.class);
  }

  @Override
  protected void init() {
    super.init();
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item item, CreativeTabs p_149666_2_, List list) {
    list.add(new ItemStack(this, 1, 0));
  }

  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity te = getTileEntity(world, new BlockPos(x, y, z));
    if (!(te instanceof TilePMon)) {
      return null;
    }
    return new ContainerPMon(player.inventory, (TilePMon) te);
  }

  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity te = getTileEntity(world, new BlockPos(x, y, z));
    final InventoryPlayer inventory = player.inventory;
    if (te instanceof TilePMon && inventory != null) {
      return new GuiPMon(inventory, (TilePMon) te);
    }
    return null;
  }

  @Override
  public boolean isOpaqueCube() {
    return false;
  }

  @Override
  protected int getGuiId() {
    return crazypants.enderio.GuiHandler.GUI_ID_POWER_MONITOR2;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addCommonEntries(ItemStack itemstack, EntityPlayer entityplayer, List<String> list, boolean flag) {
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addBasicEntries(ItemStack itemstack, EntityPlayer entityplayer, List<String> list, boolean flag) {
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addDetailedEntries(ItemStack itemstack, EntityPlayer entityplayer, List<String> list, boolean flag) {
    SpecialTooltipHandler.addDetailedTooltipFromResources(list, itemstack);
  }

  @Override
  protected void setBlockStateWrapperCache(@Nonnull IBlockStateWrapper blockStateWrapper, @Nonnull IBlockAccess world, @Nonnull BlockPos pos,
      @Nonnull TilePMon tileEntity) {
    blockStateWrapper.addCacheKey(tileEntity.getFacing());
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
  }

}
