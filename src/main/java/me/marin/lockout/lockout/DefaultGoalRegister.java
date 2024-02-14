package me.marin.lockout.lockout;

import me.marin.lockout.Lockout;
import me.marin.lockout.generator.GoalDataGenerator;
import me.marin.lockout.generator.GoalRequirementsProvider;
import me.marin.lockout.lockout.goals.advancement.*;
import me.marin.lockout.lockout.goals.advancement.unique.*;
import me.marin.lockout.lockout.goals.biome.*;
import me.marin.lockout.lockout.goals.breed_animals.*;
import me.marin.lockout.lockout.goals.brewing.*;
import me.marin.lockout.lockout.goals.consume.*;
import me.marin.lockout.lockout.goals.consume.unique.*;
import me.marin.lockout.lockout.goals.death.*;
import me.marin.lockout.lockout.goals.experience.*;
import me.marin.lockout.lockout.goals.have_more.HaveMostUniqueCraftsGoal;
import me.marin.lockout.lockout.goals.kill.*;
import me.marin.lockout.lockout.goals.kill.unique.*;
import me.marin.lockout.lockout.goals.mine.*;
import me.marin.lockout.lockout.goals.dimension.*;
import me.marin.lockout.lockout.goals.misc.*;
import me.marin.lockout.lockout.goals.have_more.HaveMostXPLevelsGoal;
import me.marin.lockout.lockout.goals.obtain.*;
import me.marin.lockout.lockout.goals.opponent.*;
import me.marin.lockout.lockout.goals.ride.*;
import me.marin.lockout.lockout.goals.status_effect.*;
import me.marin.lockout.lockout.goals.status_effect.unique.*;
import me.marin.lockout.lockout.goals.tame_animal.*;
import me.marin.lockout.lockout.goals.util.GoalDataConstants;
import me.marin.lockout.lockout.goals.wear_armor.*;
import me.marin.lockout.lockout.goals.workstation.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.DyeColor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureKeys;

import java.util.List;

import static me.marin.lockout.lockout.GoalRegistry.*;

public class DefaultGoalRegister {

    public static void registerGoals() {
        INSTANCE.register(GoalType.MINE_DIAMOND_ORE, MineDiamondOreGoal.class);
        INSTANCE.register(GoalType.MINE_EMERALD_ORE, MineEmeraldOreGoal.class,
                new GoalRequirementsProvider() {
                    // check for: Jagged Peaks, Frozen Peaks, Stony Peaks, Grove, Snowy slopes
                    @Override
                    public List<RegistryKey<Biome>> getRequiredBiomes() {
                        return List.of(BiomeKeys.JAGGED_PEAKS, BiomeKeys.FROZEN_PEAKS, BiomeKeys.STONY_PEAKS, BiomeKeys.GROVE, BiomeKeys.SNOWY_SLOPES);
                    }

                    @Override
                    public List<RegistryKey<Structure>> getRequiredStructures() {
                        return null;
                    }
                });
        INSTANCE.register(GoalType.MINE_MOB_SPAWNER, MineMobSpawnerGoal.class);
        INSTANCE.register(GoalType.MINE_TURTLE_EGG, MineTurtleEggGoal.class,
                new GoalRequirementsProvider() {
                    @Override
                    public List<RegistryKey<Biome>> getRequiredBiomes() {
                        return List.of(BiomeKeys.BEACH);
                    }

                    @Override
                    public List<RegistryKey<Structure>> getRequiredStructures() {
                        return null;
                    }
                });
        INSTANCE.register(GoalType.ENTER_NETHER, EnterNetherGoal.class);
        INSTANCE.register(GoalType.ENTER_END, EnterEndGoal.class);
        INSTANCE.register(GoalType.KILL_ENDER_DRAGON, GetFreeTheEndAdvancementGoal.class);
        INSTANCE.register(GoalType.OBTAIN_WOODEN_TOOLS, ObtainWoodenToolsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_STONE_TOOLS, ObtainStoneToolsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_IRON_TOOLS, ObtainIronToolsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_GOLDEN_TOOLS, ObtainGoldenToolsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_DIAMOND_TOOLS, ObtainDiamondToolsGoal.class);
        INSTANCE.register(GoalType.FILL_ARMOR_STAND, FillArmorStandGoal.class);
        INSTANCE.register(GoalType.WEAR_LEATHER_ARMOR, WearLeatherArmorGoal.class);
        INSTANCE.register(GoalType.WEAR_GOLDEN_ARMOR, WearGoldenArmorGoal.class);
        INSTANCE.register(GoalType.WEAR_DIAMOND_ARMOR, WearDiamondArmorGoal.class);
        INSTANCE.register(GoalType.WEAR_IRON_ARMOR, WearIronArmorGoal.class);
        INSTANCE.register(GoalType.WEAR_CHAIN_ARMOR_PIECE, WearChainArmorPieceGoal.class,
                GoalRequirementsProvider.VILLAGE);
        INSTANCE.register(GoalType.WEAR_COLORED_LEATHER_ARMOR_PIECE, WearColoredLeatherPieceGoal.class, null,
                GoalDataGenerator.builder().withLeatherArmorPiece((leatherArmor) -> leatherArmor.get(Lockout.random.nextInt(0, leatherArmor.size())))
                        .withDye(attainableDyes -> {
                            attainableDyes.remove(DyeColor.WHITE);
                            attainableDyes.remove(DyeColor.GRAY);
                            attainableDyes.remove(DyeColor.BLACK);
                            attainableDyes.remove(DyeColor.PINK);
                            attainableDyes.remove(DyeColor.LIGHT_GRAY);
                            return GoalDataConstants.getDyeColorDataString(attainableDyes.get(Lockout.random.nextInt(0, attainableDyes.size())));
                        })
        );
        INSTANCE.register(GoalType.TAME_CAT, TameCatGoal.class,
                GoalRequirementsProvider.VILLAGE);
        INSTANCE.register(GoalType.TAME_PARROT, TameParrotGoal.class,
                GoalRequirementsProvider.JUNGLE_BIOMES);
        INSTANCE.register(GoalType.TAME_HORSE, TameHorseGoal.class);
        INSTANCE.register(GoalType.TAME_WOLF, TameWolfGoal.class);
        INSTANCE.register(GoalType.BREED_4_UNIQUE_ANIMALS, Breed4UniqueAnimalsGoal.class);
        INSTANCE.register(GoalType.BREED_6_UNIQUE_ANIMALS, Breed6UniqueAnimalsGoal.class);
        INSTANCE.register(GoalType.BREED_8_UNIQUE_ANIMALS, Breed8UniqueAnimalsGoal.class);
        INSTANCE.register(GoalType.BREED_CHICKEN, BreedChickenGoal.class);
        INSTANCE.register(GoalType.BREED_COW, BreedCowsGoal.class);
        INSTANCE.register(GoalType.BREED_HOGLIN, BreedHoglinGoal.class);
        INSTANCE.register(GoalType.BREED_PIG, BreedPigGoal.class);
        INSTANCE.register(GoalType.BREED_RABBIT, BreedRabbitGoal.class,
                GoalRequirementsProvider.RABBIT_BIOMES);
        INSTANCE.register(GoalType.BREED_FOX, BreedFoxGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.SNOWY_TAIGA, BiomeKeys.GROVE);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.BREED_SHEEP, BreedSheepGoal.class);
        INSTANCE.register(GoalType.BREED_STRIDER, BreedStriderGoal.class);
        INSTANCE.register(GoalType.BREED_GOAT, BreedGoatGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.FROZEN_PEAKS, BiomeKeys.JAGGED_PEAKS, BiomeKeys.SNOWY_SLOPES);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.KILL_WITCH, KillWitchGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return null;
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return List.of(StructureKeys.SWAMP_HUT);
            }
        });
        INSTANCE.register(GoalType.KILL_ZOMBIE_VILLAGER, KillZombieVillagerGoal.class,
                GoalRequirementsProvider.VILLAGE);
        INSTANCE.register(GoalType.KILL_STRAY, KillStrayGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.FROZEN_RIVER, BiomeKeys.SNOWY_PLAINS, BiomeKeys.ICE_SPIKES);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.KILL_ZOGLIN, KillZoglinGoal.class);
        INSTANCE.register(GoalType.KILL_SILVERFISH, KillSilverfishGoal.class);
        INSTANCE.register(GoalType.KILL_GUARDIAN, KillGuardianGoal.class,
                GoalRequirementsProvider.MONUMENT);
        INSTANCE.register(GoalType.KILL_GHAST, KillGhastGoal.class);
        INSTANCE.register(GoalType.KILL_SNOW_GOLEM, KillSnowGolemGoal.class);
        INSTANCE.register(GoalType.KILL_SNOW_GOLEM_IN_NETHER, KillSnowGolemInNetherGoal.class);
        INSTANCE.register(GoalType.KILL_ELDER_GUARDIAN, KillElderGuardianGoal.class,
                GoalRequirementsProvider.MONUMENT);
        INSTANCE.register(GoalType.KILL_COLORED_SHEEP, KillColoredSheepGoal.class, null,
                GoalDataGenerator.builder().withDye(attainableDyes -> {
                    attainableDyes.remove(DyeColor.WHITE);
                    attainableDyes.remove(DyeColor.GRAY);
                    attainableDyes.remove(DyeColor.BLACK);
                    attainableDyes.remove(DyeColor.LIGHT_GRAY);
                    attainableDyes.remove(DyeColor.PINK);
                    attainableDyes.remove(DyeColor.BROWN);
                    return GoalDataConstants.getDyeColorDataString(attainableDyes.get(Lockout.random.nextInt(0, attainableDyes.size())));
                })
        );
        INSTANCE.register(GoalType.KILL_7_UNIQUE_HOSTILE_MOBS, Kill7UniqueHostileMobsGoal.class);
        INSTANCE.register(GoalType.KILL_10_UNIQUE_HOSTILE_MOBS, Kill10UniqueHostileMobsGoal.class);
        INSTANCE.register(GoalType.KILL_13_UNIQUE_HOSTILE_MOBS, Kill13UniqueHostileMobsGoal.class);
        INSTANCE.register(GoalType.KILL_15_UNIQUE_HOSTILE_MOBS, Kill15UniqueHostileMobsGoal.class);
        INSTANCE.register(GoalType.KILL_30_UNDEAD_MOBS, Kill30UndeadMobsGoal.class);
        INSTANCE.register(GoalType.KILL_20_ARTHROPOD_MOBS, Kill20ArthropodMobsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_RED_NETHER_BRICK_STAIRS, ObtainRedNetherBrickStairsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_TROPICAL_FISH_BUCKET, ObtainBucketOfTropicalFishGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.LUSH_CAVES, BiomeKeys.MANGROVE_SWAMP);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.OBTAIN_BOOKSHELF, ObtainBookshelfGoal.class);
        INSTANCE.register(GoalType.OBTAIN_MOSSY_STONE_BRICK_WALL, ObtainMossyStoneBrickWallGoal.class);
        INSTANCE.register(GoalType.OBTAIN_FLOWERING_AZALEA, ObtainFloweringAzaleaGoal.class);
        INSTANCE.register(GoalType.OBTAIN_SCAFFOLDING, ObtainScaffoldingGoal.class);
        INSTANCE.register(GoalType.OBTAIN_END_CRYSTAL, ObtainEndCrystalGoal.class);
        INSTANCE.register(GoalType.OBTAIN_BELL, ObtainBellGoal.class,
                GoalRequirementsProvider.VILLAGE);
        INSTANCE.register(GoalType.OBTAIN_ENCHANT_BOTTLE, ObtainEnchantBottleGoal.class);
        INSTANCE.register(GoalType.OBTAIN_POWDER_SNOW_BUCKET, ObtainPowderSnowBucketGoal.class); // what biomes?
        INSTANCE.register(GoalType.OBTAIN_SOUL_LANTERN, ObtainSoulLanternGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ANCIENT_DEBRIS, ObtainAncientDebrisGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ENDER_CHEST, ObtainEnderChestGoal.class);
        INSTANCE.register(GoalType.OBTAIN_HEART_OF_THE_SEA, ObtainHeartOfTheSeaGoal.class);
        INSTANCE.register(GoalType.OBTAIN_WITHER_SKELETON_SKULL, ObtainWitherSkeletonSkullGoal.class);
        INSTANCE.register(GoalType.OBTAIN_END_ROD, ObtainEndRodGoal.class);
        INSTANCE.register(GoalType.OBTAIN_SPONGE, ObtainSpongeGoal.class,
                GoalRequirementsProvider.MONUMENT);
        INSTANCE.register(GoalType.OBTAIN_DRAGON_EGG, ObtainDragonEggGoal.class);
        INSTANCE.register(GoalType.OBTAIN_TNT, ObtainTNTGoal.class);
        INSTANCE.register(GoalType.OBTAIN_COBWEB, ObtainCobwebGoal.class);
        INSTANCE.register(GoalType.OBTAIN_MUD_BRICK_WALL, ObtainMudBrickWallGoal.class);
        INSTANCE.register(GoalType.OBTAIN_DAYLIGHT_DETECTOR, ObtainDaylightDetectorGoal.class);
        INSTANCE.register(GoalType.OBTAIN_REDSTONE_REPEATER, ObtainRedstoneRepeaterGoal.class);
        INSTANCE.register(GoalType.OBTAIN_REDSTONE_COMPARATOR, ObtainRedstoneComparatorGoal.class);
        INSTANCE.register(GoalType.OBTAIN_OBSERVER, ObtainObserverGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ACTIVATOR_RAIL, ObtainActivatorRailGoal.class);
        INSTANCE.register(GoalType.OBTAIN_DETECTOR_RAIL, ObtainDetectorRailGoal.class);
        INSTANCE.register(GoalType.OBTAIN_POWERED_RAIL, ObtainPoweredRailGoal.class);
        INSTANCE.register(GoalType.OBTAIN_DISPENSER, ObtainDispenserGoal.class);
        INSTANCE.register(GoalType.OBTAIN_PISTON, ObtainPistonGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ALL_RAW_ORE_BLOCKS, ObtainAllRawOreBlocksGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ALL_HORSE_ARMOR, ObtainAllHorseArmorGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ALL_SEEDS, ObtainAllSeedsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_6_UNIQUE_FLOWERS, Obtain6UniqueFlowersGoal.class);
        INSTANCE.register(GoalType.OBTAIN_COLORED_GLAZED_TERRACOTTA, ObtainColoredGlazedTerracottaGoal.class,
                null,
                GoalDataGenerator.builder().withDye(attainableDyes -> GoalDataConstants.getDyeColorDataString(attainableDyes.get(Lockout.random.nextInt(0, attainableDyes.size()))))
        );
        INSTANCE.register(GoalType.OBTAIN_64_COLORED_WOOL, Obtain64ColoredWoolGoal.class,
                null,
                GoalDataGenerator.builder().withDye(attainableDyes -> GoalDataConstants.getDyeColorDataString(attainableDyes.get(Lockout.random.nextInt(0, attainableDyes.size()))))
        );
        INSTANCE.register(GoalType.OBTAIN_64_COLORED_CONCRETE, Obtain64ColoredConcreteGoal.class,
                null,
                GoalDataGenerator.builder().withDye(attainableDyes -> GoalDataConstants.getDyeColorDataString(attainableDyes.get(Lockout.random.nextInt(0, attainableDyes.size()))))
        );
        INSTANCE.register(GoalType.OBTAIN_WRITTEN_BOOK, ObtainWrittenBookGoal.class);
        INSTANCE.register(GoalType.FILL_INVENTORY_UNIQUE_ITEMS, FillInventoryWithUniqueItemsGoal.class);
        INSTANCE.register(GoalType.GET_THIS_BOAT_HAS_LEGS_ADVANCEMENT, GetThisBoatHasLegsAdvancementGoal.class);
        INSTANCE.register(GoalType.WEAR_CARVED_PUMPKIN_FOR_5_MINUTES, WearCarvedPumpkinFor5MinutesGoal.class);
        INSTANCE.register(GoalType.USE_BREWING_STAND, GetLocalBreweryAdvancementGoal.class);
        INSTANCE.register(GoalType.BREW_HEALING_POTION, BrewHealingPotionGoal.class);
        INSTANCE.register(GoalType.BREW_INVISIBILITY_POTION, BrewInvisibilityPotionGoal.class);
        INSTANCE.register(GoalType.BREW_POISON_POTION, BrewPoisonPotionGoal.class);
        INSTANCE.register(GoalType.BREW_SWIFTNESS_POTION, BrewSwiftnessPotionGoal.class);
        INSTANCE.register(GoalType.BREW_WATER_BREATHING_POTION, BrewWaterBreathingPotionGoal.class);
        INSTANCE.register(GoalType.BREW_WEAKNESS_POTION, BrewWeaknessPotionGoal.class);
        INSTANCE.register(GoalType.BREW_LINGERING_POTION, BrewLingeringPotionGoal.class);
        INSTANCE.register(GoalType.VISIT_ICE_SPIKES_BIOME, VisitIceSpikesBiomeGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.ICE_SPIKES);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.VISIT_BADLANDS_BIOME, VisitBadlandsBiomeGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS, BiomeKeys.WOODED_BADLANDS);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.VISIT_MUSHROOM_BIOME, VisitMushroomBiomeGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.MUSHROOM_FIELDS);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.EAT_5_UNIQUE_FOOD, Eat5UniqueFoodsGoal.class);
        INSTANCE.register(GoalType.EAT_10_UNIQUE_FOOD, Eat10UniqueFoodsGoal.class);
        INSTANCE.register(GoalType.EAT_15_UNIQUE_FOOD, Eat15UniqueFoodsGoal.class);
        INSTANCE.register(GoalType.EAT_20_UNIQUE_FOOD, Eat20UniqueFoodsGoal.class);
        INSTANCE.register(GoalType.EAT_25_UNIQUE_FOOD, Eat25UniqueFoodsGoal.class);
        INSTANCE.register(GoalType.EAT_CHORUS_FRUIT, EatChorusFruitGoal.class);
        INSTANCE.register(GoalType.EAT_COOKIE, EatCookieGoal.class);
        INSTANCE.register(GoalType.EAT_GLOW_BERRY, EatGlowBerryGoal.class);
        INSTANCE.register(GoalType.EAT_POISONOUS_POTATO, EatPoisonousPotatoGoal.class);
        INSTANCE.register(GoalType.EAT_PUMPKIN_PIE, EatPumpkinPieGoal.class);
        INSTANCE.register(GoalType.EAT_RABBIT_STEW, EatRabbitStewGoal.class);
        INSTANCE.register(GoalType.EAT_SUSPICIOUS_STEW, EatSuspiciousStewGoal.class);
        INSTANCE.register(GoalType.DRINK_HONEY_BOTTLE, DrinkHoneyBottleGoal.class);
        INSTANCE.register(GoalType.EAT_CAKE, EatCakeGoal.class);
        INSTANCE.register(GoalType.OBTAIN_4_UNIQUE_SAPLINGS, Obtain4UniqueSaplingsGoal.class);
        INSTANCE.register(GoalType.TOOT_GOAT_HORN, TootGoatHornGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.FROZEN_PEAKS, BiomeKeys.JAGGED_PEAKS, BiomeKeys.SNOWY_SLOPES);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.GET_ANY_SPYGLASS_ADVANCEMENT, GetAnySpyglassAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_BULLSEYE_ADVANCEMENT, GetBullseyeAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_HOT_TOURIST_DESTINATIONS_ADVANCEMENT, GetHotTouristDestinationsAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_NOT_QUITE_NINE_LIVES_ADVANCEMENT, GetNotQuiteNineLivesAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_OH_SHINY_ADVANCEMENT, GetOhShinyAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_SNIPER_DUEL_ADVANCEMENT, GetSniperDuelAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_WHAT_A_DEAL_ADVANCEMENT, GetWhatADealAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_ABSORPTION_STATUS_EFFECT, GetAbsorptionStatusEffectGoal.class);
        INSTANCE.register(GoalType.GET_BAD_OMEN_STATUS_EFFECT, GetBadOmenStatusEffectGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return null;
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return List.of(StructureKeys.PILLAGER_OUTPOST);
            }
        });
        INSTANCE.register(GoalType.GET_GLOWING_STATUS_EFFECT, GetGlowingStatusEffectGoal.class);
        INSTANCE.register(GoalType.GET_JUMP_BOOST_STATUS_EFFECT, GetJumpBoostStatusEffectGoal.class);
        INSTANCE.register(GoalType.GET_LEVITATION_STATUS_EFFECT, GetLevitationStatusEffectGoal.class);
        INSTANCE.register(GoalType.GET_MINING_FATIGUE_STATUS_EFFECT, GetMiningFatigueStatusEffectGoal.class,
                GoalRequirementsProvider.MONUMENT);
        INSTANCE.register(GoalType.GET_NAUSEA_STATUS_EFFECT, GetNauseaStatusEffectGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.GET_POISON_STATUS_EFFECT, GetPoisonStatusEffectGoal.class);
        INSTANCE.register(GoalType.GET_WEAKNESS_STATUS_EFFECT, GetWeaknessStatusEffectGoal.class);
        INSTANCE.register(GoalType.DIE_BY_ANVIL, DieByAnvilGoal.class);
        INSTANCE.register(GoalType.DIE_BY_BEE_STING, DieByBeeStingGoal.class);
        INSTANCE.register(GoalType.DIE_BY_BERRY_BUSH, DieByBerryBushGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.TAIGA, BiomeKeys.SNOWY_TAIGA);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.DIE_BY_CACTUS, DieByCactusGoal.class, new GoalRequirementsProvider() {
            @Override
            public List<RegistryKey<Biome>> getRequiredBiomes() {
                return List.of(BiomeKeys.DESERT, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS, BiomeKeys.ERODED_BADLANDS);
            }

            @Override
            public List<RegistryKey<Structure>> getRequiredStructures() {
                return null;
            }
        });
        INSTANCE.register(GoalType.DIE_BY_FALLING_OFF_VINE, DieByFallingOffVinesGoal.class);
        INSTANCE.register(GoalType.DIE_BY_FALLING_STALACTITE, DieByFallingStalactiteGoal.class);
        INSTANCE.register(GoalType.DIE_BY_FIREWORK, DieByFireworkGoal.class);
        INSTANCE.register(GoalType.DIE_BY_INTENTIONAL_GAME_DESIGN, DieByIntentionalGameDesignGoal.class);
        INSTANCE.register(GoalType.DIE_BY_IRON_GOLEM, DieByIronGolemGoal.class);
        INSTANCE.register(GoalType.DIE_BY_MAGIC, DieByMagicGoal.class,
                GoalRequirementsProvider.MONUMENT);
        INSTANCE.register(GoalType.DIE_BY_TNT_MINECART, DieByTNTMinecartGoal.class);
        INSTANCE.register(GoalType.GET_A_TERRIBLE_FORTRESS_ADVANCEMENT, GetATerribleFortressAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_THE_CITY_AT_THE_END_OF_THE_GAME_ADVANCEMENT, GetCityAtTheEndOfTheGameAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_EYE_SPY_ADVANCEMENT, GetEyeSpyAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_THOSE_WERE_THE_DAYS_ADVANCEMENT, GetThoseWereTheDaysAdvancementGoal.class);
        INSTANCE.register(GoalType.REMOVE_STATUS_EFFECT_USING_MILK, RemoveStatusEffectUsingMilkGoal.class);
        INSTANCE.register(GoalType.GET_3_STATUS_EFFECTS_AT_ONCE, Get3StatusEffectsGoal.class);
        INSTANCE.register(GoalType.GET_4_STATUS_EFFECTS_AT_ONCE, Get4StatusEffectsGoal.class);
        INSTANCE.register(GoalType.GET_6_STATUS_EFFECTS_AT_ONCE, Get6StatusEffectsGoal.class);
        INSTANCE.register(GoalType.REACH_EXP_LEVEL_15, ReachXPLevel15Goal.class);
        INSTANCE.register(GoalType.REACH_EXP_LEVEL_30, ReachXPLevel30Goal.class);
        INSTANCE.register(GoalType.RIDE_HORSE, RideHorseGoal.class);
        INSTANCE.register(GoalType.RIDE_MINECART, RideMinecartGoal.class);
        INSTANCE.register(GoalType.RIDE_PIG, RidePigGoal.class);
        INSTANCE.register(GoalType.USE_STONECUTTER, UseStonecutterGoal.class);
        INSTANCE.register(GoalType.USE_ANVIL, UseAnvilGoal.class);
        INSTANCE.register(GoalType.USE_ENCHANTING_TABLE, UseEnchantingTableGoal.class);
        INSTANCE.register(GoalType.USE_GRINDSTONE, UseGrindstoneGoal.class);
        INSTANCE.register(GoalType.USE_LOOM, UseLoomGoal.class);
        INSTANCE.register(GoalType.USE_SMITHING_TABLE, UseSmithingTableGoal.class);
        INSTANCE.register(GoalType.USE_CAULDRON, UseCauldronGoal.class);
        INSTANCE.register(GoalType.USE_COMPOSTER, UseComposterGoal.class);
        INSTANCE.register(GoalType.USE_JUKEBOX, UseJukeboxGoal.class);
        INSTANCE.register(GoalType.USE_GLOW_INK, UseGlowInkGoal.class);
        INSTANCE.register(GoalType.EMPTY_HUNGER_BAR, EmptyHungerBarGoal.class);
        INSTANCE.register(GoalType.REACH_HEIGHT_LIMIT, ReachHeightLimitGoal.class);
        INSTANCE.register(GoalType.REACH_BEDROCK, ReachBedrockGoal.class);
        INSTANCE.register(GoalType.OBTAIN_CLOCK, ObtainClockGoal.class);
        INSTANCE.register(GoalType.OBTAIN_6_UNIQUE_BUCKETS, Obtain6UniqueBucketsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ALL_MINECARTS, ObtainAllMinecartsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ALL_MUSHROOMS, ObtainAllMushroomsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_7_UNIQUE_WORKSTATIONS, Obtain7WorkstationsGoal.class);
        INSTANCE.register(GoalType.OBTAIN_REDSTONE_LAMP, ObtainRedstoneLampGoal.class);
        INSTANCE.register(GoalType.OBTAIN_SOUL_CAMPFIRE, ObtainSoulCampfireGoal.class);
        INSTANCE.register(GoalType.OBTAIN_ALL_PUMPKINS, ObtainAllPumpkinsGoal.class);
        INSTANCE.register(GoalType.ENRAGE_ZOMBIFIED_PIGLIN, AngerZombifiedPiglinGoal.class);
        INSTANCE.register(GoalType.OBTAIN_BRICK_WALL, ObtainBrickWallGoal.class);
        INSTANCE.register(GoalType.GET_10_ADVANCEMENTS, Get10UniqueAdvancementsGoal.class);
        INSTANCE.register(GoalType.GET_20_ADVANCEMENTS, Get20UniqueAdvancementsGoal.class);
        INSTANCE.register(GoalType.GET_30_ADVANCEMENTS, Get30UniqueAdvancementsGoal.class);
        INSTANCE.register(GoalType.WEAR_UNIQUE_COLORED_LEATHER_ARMOR, WearUniqueColoredLeatherArmorGoal.class);
        INSTANCE.register(GoalType.KILL_OTHER_PLAYER, KillOtherTeamPlayer.class,
                GoalRequirementsProvider.TEAMS_GOAL);
        INSTANCE.register(GoalType.OPPONENT_OBTAINS_CRAFTING_TABLE, OpponentObtainsCraftingTableGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_OBTAINS_OBSIDIAN, OpponentObtainsObsidianGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_OBTAINS_SEEDS, OpponentObtainsSeedsGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_CATCHES_ON_FIRE, OpponentCatchesOnFireGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_DIES_3_TIMES, OpponentDies3TimesGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_DIES, OpponentDiesGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_HIT_BY_EGG, OpponentHitByEggGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_HIT_BY_SNOWBALL, OpponentHitBySnowballGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        // INSTANCE.register(GoalType.OPPONENT_JUMPS, OpponentJumpsGoal.class);
        INSTANCE.register(GoalType.OPPONENT_TAKES_100_DAMAGE, OpponentTakes100DamageGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_TAKES_FALL_DAMAGE, OpponentTakesFallDamageGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);
        INSTANCE.register(GoalType.OPPONENT_TOUCHES_WATER, OpponentTouchesWaterGoal.class,
                GoalRequirementsProvider.TO2_ONLY_GOAL);

        INSTANCE.register(GoalType.TAKE_200_DAMAGE, Take200DamageGoal.class);
        INSTANCE.register(GoalType.REACH_NETHER_ROOF, ReachNetherRoofGoal.class);
        INSTANCE.register(GoalType.HAVE_MORE_XP_LEVELS, HaveMostXPLevelsGoal.class,
                GoalRequirementsProvider.TEAMS_GOAL);
        INSTANCE.register(GoalType.FREEZE_TO_DEATH, DieByFreezingGoal.class); // same as powder snow?
        INSTANCE.register(GoalType.KILL_100_MOBS, Kill100MobsGoal.class);
        INSTANCE.register(GoalType.DEAL_400_DAMAGE, Deal400DamageGoal.class);
        INSTANCE.register(GoalType.SPRINT_1_KM, Sprint1KmGoal.class);
        INSTANCE.register(GoalType.GET_HIRED_HELP_ADVANCEMENT, GetHiredHelpAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_WAX_ON_ADVANCEMENT, GetWaxOnAdvancementGoal.class);
        INSTANCE.register(GoalType.GET_WAX_OFF_ADVANCEMENT, GetWaxOffAdvancementGoal.class);
        INSTANCE.register(GoalType.PUT_BANNER_ON_SHIELD, ObtainShieldWithBannerGoal.class);
        INSTANCE.register(GoalType.HAVE_MORE_UNIQUE_CRAFTS, HaveMostUniqueCraftsGoal.class,
                GoalRequirementsProvider.TEAMS_GOAL);
        INSTANCE.register(GoalType.HAVE_YOUR_SHIELD_DISABLED, HaveShieldDisabledGoal.class);
        INSTANCE.register(GoalType.ITEM_FRAME_IN_ITEM_FRAME, ItemFrameInItemFrameGoal.class);
        INSTANCE.register(GoalType.FILL_CAMPFIRE, FillCampfireWithFoodGoal.class);
        INSTANCE.register(GoalType.PUT_FLOWER_IN_POT, PutFlowerInPotGoal.class);
        INSTANCE.register(GoalType.KILL_ALL_RAID_MOBS, KillAllRaidMobsGoal.class,
                new GoalRequirementsProvider() {
                    @Override
                    public List<RegistryKey<Biome>> getRequiredBiomes() {
                        return null;
                    }

                    @Override
                    public List<RegistryKey<Structure>> getRequiredStructures() {
                        return List.of(StructureKeys.PILLAGER_OUTPOST, StructureKeys.MANSION);
                    }
                });

    }

}
