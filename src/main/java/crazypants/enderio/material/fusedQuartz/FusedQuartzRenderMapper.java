package crazypants.enderio.material.fusedQuartz;

import java.util.ArrayList;
import java.util.List;

import static crazypants.enderio.render.EnumMergingBlockRenderMode.RENDER;

import crazypants.enderio.render.EnumMergingBlockRenderMode;
import crazypants.enderio.render.IRenderMapper;
import crazypants.enderio.render.MergingBlockStateWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class FusedQuartzRenderMapper implements IRenderMapper {

  public FusedQuartzRenderMapper() {
  }

  public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
    return new FusedQuartzStateWrapper(state, world, pos);
  }

  @Override
  public List<IBlockState> mapBlockRender(IBlockState state, IBlockAccess world, BlockPos pos) {
    if (state instanceof MergingBlockStateWrapper) {
      return ((MergingBlockStateWrapper) state).getStates();
    }
    return null;
  }

  @Override
  public List<IBlockState> mapBlockRender(Block block, ItemStack stack) {
    List<IBlockState> states = new ArrayList<IBlockState>();
    IBlockState defaultState = block.getDefaultState();
    FusedQuartzType bankType = FusedQuartzType.getTypeFromMeta(stack.getItemDamage());
    defaultState = defaultState.withProperty(FusedQuartzType.KIND, bankType);

    states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.sides));

    for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL) {
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, EnumFacing.UP)));
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, EnumFacing.DOWN)));
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, facing.rotateYCCW())));
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, facing.rotateYCCW(), EnumFacing.UP)));
      states.add(defaultState.withProperty(RENDER, EnumMergingBlockRenderMode.get(facing, facing.rotateYCCW(), EnumFacing.DOWN)));
    }
    return states;
  }

}
