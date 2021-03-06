package crazypants.enderio.base.config.config;

import crazypants.enderio.base.EnderIO;
import crazypants.enderio.base.config.factory.ValueFactory;

public final class BaseConfig {

  public static final ValueFactory F = new ValueFactory(EnderIO.MODID);

  public static void load() {
    DiagnosticsConfig.F.getClass();
    EnchantmentConfig.F.getClass();
    PersonalConfig.F.getClass();
    DarkSteelConfig.F_DARK_STEEL.getClass();
    EnchanterConfig.F.getClass();
    FluidConfig.F.getClass();
    InfinityConfig.F.getClass();
    IntegrationConfig.F.getClass();
    ItemConfig.F.getClass();
    RecipeConfig.F.getClass();
    BrokenSpawnerConfig.F.getClass();
    TeleportConfig.F.getClass();
    TopConfig.F.getClass();
    BlockConfig.F.getClass();
  }

}
