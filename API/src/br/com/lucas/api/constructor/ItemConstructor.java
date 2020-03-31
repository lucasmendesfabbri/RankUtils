package br.com.lucas.api.constructor;


import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemConstructor {

	public ItemStack ItemConstructors(Material material, String display, String lores) {
		ItemStack i = new ItemStack(material);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(display);
		java.util.List<String> lore = new ArrayList<String>();
		lore.add(lores);
		m.setLore(lore);
		i.setItemMeta(m);
		return i;
	}
	
}
