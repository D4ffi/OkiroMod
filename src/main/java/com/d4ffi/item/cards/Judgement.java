package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

import java.util.UUID;

public class Judgement extends TarotCardManager {

    public Judgement(Settings settings) {
        super(settings);
    }

    private static final UUID HEALTH_MODIFIER_ID = UUID.fromString("d5d14e1d-949b-4e2c-a936-1d5e1c8e8c82");

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();

        if (player != null && !player.getWorld().isClient) {
            // Obtener la instancia del atributo de salud máxima
            EntityAttributeInstance healthAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

            if (healthAttribute != null) {
                // Verificar si el modificador ya existe
                EntityAttributeModifier existingModifier = healthAttribute.getModifier(HEALTH_MODIFIER_ID);

                if (existingModifier == null) {
                    // Crear nuevo modificador (2.0 = 1 corazón, ya que cada corazón es 2 puntos de salud)
                    EntityAttributeModifier healthModifier = new EntityAttributeModifier(
                            HEALTH_MODIFIER_ID,
                            "Extra Heart",
                            2.0D,
                            EntityAttributeModifier.Operation.ADDITION
                    );

                    // Agregar el modificador
                    healthAttribute.addPersistentModifier(healthModifier);

                    // Curar al jugador por la cantidad agregada
                    player.heal(2.0F);

                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.PASS;
    }
}
