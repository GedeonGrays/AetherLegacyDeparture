package com.gildedgames.the_aether.network.packets;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.player.PlayerAether;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketUpdateDexterityShardCount extends AetherPacket<PacketUpdateDexterityShardCount>
{
    private int entityID;

    private int count;

    public PacketUpdateDexterityShardCount()
    {

    }

    public PacketUpdateDexterityShardCount(EntityPlayer thePlayer, int count)
    {
        this.entityID = thePlayer.getEntityId();
        this.count = count;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.entityID = buf.readInt();
        this.count = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.entityID);
        buf.writeInt(this.count);
    }

    @Override
    public void handleClient(PacketUpdateDexterityShardCount message, EntityPlayer player)
    {
        if (player != null && player.worldObj != null)
        {
            Entity entity = player.worldObj.getEntityByID(message.entityID);

            if (entity instanceof EntityPlayer parent)
            {

				IPlayerAether iPlayerAether = AetherAPI.get(parent);

                if (iPlayerAether != null)
                {
                    PlayerAether playerAether = (PlayerAether) iPlayerAether;

                    playerAether.shardCount = message.count;
                }
            }
        }
    }

    @Override
    public void handleServer(PacketUpdateDexterityShardCount message, EntityPlayer player)
    {

    }
}
