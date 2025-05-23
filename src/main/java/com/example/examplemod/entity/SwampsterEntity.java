package com.example.examplemod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SwampsterEntity extends Zombie {

    public SwampsterEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this)); // Swim and float
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false)); // Standard melee attack
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D)); // Wander
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F)); // Look at player
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this)); // Look around randomly

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)); // Target whatever hurt it
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true)); // Target nearest player
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.28F); // Standard Zombie speed is 0.23F, this is faster
        // MAX_HEALTH and ATTACK_DAMAGE will use the defaults from Zombie.createAttributes()
    }
}
