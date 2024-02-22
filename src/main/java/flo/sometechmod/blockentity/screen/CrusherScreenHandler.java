package flo.sometechmod.blockentity.screen;

import flo.sometechmod.blockentity.CrusherBlockEntity;
import flo.sometechmod.registry.ModScreens;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CrusherScreenHandler extends ScreenHandler
{
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final CrusherBlockEntity blockEntity;

    public CrusherScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf)
    {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(buf.readBlockPos()), new ArrayPropertyDelegate(2));
    }

    // Inventory
    public CrusherScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate)
    {
        super(ModScreens.CRUSHER_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 2);
        this.inventory = (Inventory)blockEntity;
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = ((CrusherBlockEntity) blockEntity);

        // Crusher Inventory
        this.addSlot(new Slot(inventory, 0, 56, 35));
        this.addSlot(new Slot(inventory, 1, 116, 35));

        // Player Inventory
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Hotbar Inventory
        for (int k = 0; k < 9; ++k)
        {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        addProperties(propertyDelegate);
    }

    // Crafting Animation
    public boolean isCrafting() { return propertyDelegate.get(0) > 0; }

    public int getArrowProgress()
    {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int arrowProgress = 26;

        return maxProgress != 0 && progress != 0 ? progress * arrowProgress / maxProgress : 0;
    }

    // Shift Click to drag the entire stack.
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot)
    {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack())
        {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size())
            {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.insertItem(originalStack, 0, this.inventory.size(), false))
            {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty())
            {
                slot.setStack(ItemStack.EMPTY);
            }
            else
            {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) { return this.inventory.canPlayerUse(player); }
}