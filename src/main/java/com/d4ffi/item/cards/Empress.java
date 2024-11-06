package com.d4ffi.item.cards;

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

    public Empress(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient && Objects.requireNonNull(context.getPlayer()).isSneaky()) {
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

        } catch (Exception ignored) {}
    }

    private static boolean isValidTeleportLocation(PlayerEntity player, int x, int y, int z) {
        return player.getWorld().getBlockState(new BlockPos(x, y + 1, z)).isAir() ||
                !player.getWorld().getBlockState(new BlockPos(x, y + 1, z)).isSolidBlock(player.getWorld(), new BlockPos(x, y + 1, z));
    }

    private static void teleportPlayerWithEffects(PlayerEntity player, int x, int y, int z) {
        spawnTeleportParticles(player, new Vec3d(x + 0.5, y, z + 0.5));
        player.teleport(x + 0.5, y + 1, z + 0.5);
        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 0.5F);

    }

    private static void spawnTeleportParticles(PlayerEntity player, Vec3d pos) {
        if (player.getWorld().isClient) return;

        ServerWorld world = (ServerWorld) player.getWorld();

        Random random = new Random();
        double velocityX = random.nextGaussian() * 0.02D;
        double velocityY = random.nextGaussian() * 0.02D;
        double velocityZ = random.nextGaussian() * 0.02D;

        world.spawnParticles(ParticleTypes.SCULK_SOUL, pos.getX(), pos.getY() + 1, pos.getZ(), 108, velocityX, velocityY, velocityZ, 0.1);

    }
}

