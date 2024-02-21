package flo.sometechmod.entity;

import flo.sometechmod.entity.handler.ImplementedInventory;
import flo.sometechmod.registry.ModBlockEntities;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CrusherBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory
{
    // Slots
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return items;
    }

    // Constructor
    public CrusherBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CRUSHER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
    {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.literal("Crusher");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return null;
    }

    // Saves
    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }

    // Loads
    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }
}
