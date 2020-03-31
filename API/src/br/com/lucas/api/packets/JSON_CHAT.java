package br.com.lucas.api.packets;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class JSON_CHAT {

	public void chatMessage(Player p, String message, String messageHover, String acLast, Action ac, net.md_5.bungee.api.chat.HoverEvent.Action ev) {
		IChatBaseComponent component = ChatSerializer.a("{\"text\":\""+message+"\","
                + "\"clickEvent\":{\"action\":\""+ac.toString().toLowerCase()+"\",\"value\":\""+acLast+"\"},"
                + "\"hoverEvent\":{\"action\":\""+ev.toString().toLowerCase()+"\",\"value\":\""+messageHover+"\"}}");
        PacketPlayOutChat chatPacket = new PacketPlayOutChat(component);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(chatPacket);
	}
	
}
