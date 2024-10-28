package com.d4ffi.item.cards;

import com.d4ffi.Okiro;
import com.d4ffi.network.OkiroPackets;
import com.d4ffi.tarotCard.TarotCardManager;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;
import java.util.Random;

public class Empress extends TarotCardManager {

    private static final Random random = new Random();

    public Empress(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient && context.getPlayer().isSneaky()) {
            if (context.getSide() == Direction.UP){
                context.getPlayer().getMainHandStack().getOrCreateNbt()
                        .putIntArray("Location", new int[]{
                                context.getBlockPos().getX(),
                                context.getBlockPos().getY(),
                                context.getBlockPos().getZ()});
            }
        }
        return super.useOnBlock(context);
    }

    public static void activateKeyBindAbility(PlayerEntity player) {

        try {
            Vec3d oldpos = player.getPos();
            int[] location = Objects.requireNonNull(player.getMainHandStack().getNbt()).getIntArray("Location");

            // Check if the location is valid (air or water above it)
            if (isValidTeleportLocation(player, location[0], location[1], location[2])) {
                spawnTeleportParticles(player, oldpos);
                teleportPlayerWithEffects(player, location[0], location[1], location[2]);
            } else {
                // Check for a valid location in a 3x3 area
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        if (isValidTeleportLocation(player, location[0] + x, location[1], location[2] + z)) {
                            spawnTeleportParticles(player, oldpos);
                            teleportPlayerWithEffects(player, location[0] + x, location[1], location[2] + z);
                            return;
                        } else {
                            ServerPlayNetworking.send((ServerPlayerEntity) player, OkiroPackets.EMPRESS_FAILED, PacketByteBufs.empty());
                        }
                    }
                }
            }

        } catch (Exception e) {
            Okiro.LOGGER.error("Empress failed");
        }
    }

    private static boolean isValidTeleportLocation(PlayerEntity player, int x, int y, int z) {
        return player.getWorld().getBlockState(new BlockPos(x, y + 1, z)).isAir() ||
                !player.getWorld().getBlockState(new BlockPos(x, y + 1, z)).isSolidBlock(player.getWorld(), new BlockPos(x, y + 1, z));
    }

    private static void teleportPlayerWithEffects(PlayerEntity player, int x, int y, int z) {
        player.teleport(x + 0.5, y + 1, z + 0.5);
        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 0.5F);
        spawnTeleportParticles(player, new Vec3d(x + 0.5, y, z + 0.5));
        spawnTeleportParticles(player, new Vec3d(x + 0.5, y + 1, z + 0.5));
    }

    private static void spawnTeleportParticles(PlayerEntity player, Vec3d pos) {
        if (player.getWorld().isClient) return;

        ServerWorld world = (ServerWorld) player.getWorld();

        // Generar nube de partículas
        for (int i = 0; i < 32; i++) {
            double offsetX = random.nextGaussian() * 0.2;
            double offsetY = random.nextGaussian() * 0.2;
            double offsetZ = random.nextGaussian() * 0.2;

            world.spawnParticles(
                    ParticleTypes.PORTAL,
                    pos.getX() + offsetX,
                    pos.getY() + offsetY + 1,
                    pos.getZ() + offsetZ,
                    10, // cantidad de partículas por punto
                    0, 0, 0, // velocidad de las partículas
                    0 // velocidad extra
            );
        }
    }
}

