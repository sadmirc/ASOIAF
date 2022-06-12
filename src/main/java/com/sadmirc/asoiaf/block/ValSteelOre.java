package com.sadmirc.asoiaf.block;

import com.sadmirc.asoiaf.init.DimensionInit;
import com.sadmirc.asoiaf.world.dimension.WesterosTeleporter;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ValSteelOre extends Block {
	BlockPos originalTeleport;
	public ValSteelOre(Properties p_49795_) {
		super(p_49795_);
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            if (!pPlayer.isCrouching()) {
                MinecraftServer server = pLevel.getServer();
                if (server != null) {
                    if (pLevel.dimension() == DimensionInit.dimensionLevel) {
                        ServerLevel overworld = server.getLevel(Level.OVERWORLD);
                        if (overworld != null) {
                        	//originalTeleport = pPos;
                            pPlayer.changeDimension(overworld, new WesterosTeleporter(pPos, false));
                        }
                    } else {
                        ServerLevel westeros = server.getLevel(DimensionInit.dimensionLevel);
                        if (westeros != null) {
                            //pPlayer.changeDimension(kjDim, new WesterosTeleporter(originalTeleport, true));
                            //originalTeleport = null;
                        	pPlayer.changeDimension(westeros, new WesterosTeleporter(pPos, true));
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
	
}
