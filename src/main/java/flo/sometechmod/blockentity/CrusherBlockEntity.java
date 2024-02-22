package flo.sometechmod.blockentity;

import flo.sometechmod.blockentity.handler.ImplementedInventory;
import flo.sometechmod.blockentity.screen.CrusherScreenHandler;
import flo.sometechmod.registry.ModBlockEntities;
import flo.sometechmod.registry.ModItems;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CrusherBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory
{
    // Slots
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    // PropertyDelegate synchronize Client & Server.
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100;

    public CrusherBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CRUSHER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> CrusherBlockEntity.this.progress;
                    case 1 -> CrusherBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> CrusherBlockEntity.this.progress = value;
                    case 1 -> CrusherBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size()
            {
                return 2;
            }
        };
    }

    // From the ImplementedInventory Interface.
    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return items;
    }

    // Creates the ScreenHandler itself.
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new CrusherScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    // Text that appears top left of the GUI.
    @Override
    public Text getDisplayName()
    {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
    {
        buf.writeBlockPos(this.pos);
    }

    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        Inventories.writeNbt(nbt, items);
        nbt.putInt("recipe.progress", progress);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);

        Inventories.readNbt(nbt, items);
        progress = nbt.getInt("recipe.progress");
    }

    // Updates
    public void tick(World world, BlockPos pos, BlockState state)
    {
        if (canInsert() && hasRecipe())
        {
            this.progress++;
            markDirty(world, pos, state);

            // If an item has been crafted.
            if (progress >= maxProgress)
            {
                craftItem();
                progress = 0;
            }
        }
        else
        {
            progress = 0;
        }
    }

    // Checks if there is room left in the output slot.
    private boolean canInsert()
    {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    // Checks if items can be placed in the input slot.
    private boolean hasRecipe()
    {
        return this.getStack(INPUT_SLOT).getItem() == ModItems.TIN_INGOT;
    }

    private void craftItem()
    {
        this.removeStack(INPUT_SLOT, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(ModItems.RAW_TIN, this.getStack(OUTPUT_SLOT).getCount() + 1));
    }

}
