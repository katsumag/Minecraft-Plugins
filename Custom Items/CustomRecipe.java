package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;

public class CustomRecipe extends ShapedRecipe {
  
    public static Map<ItemStack, List<CustomRecipe>> recipes = new HashMap<ItemStack, List<CustomRecipe>>();

    private Map<Character, ItemStack> exactIntegrients = new HashMap<Character, ItemStack>();
  
    public CustomRecipe(ItemStack result) {
        super(result);
    }
  
    @Override
    public CustomRecipe setIngredient(char key, Material ingredient) {
        super.setIngredient(key, ingredient);
        this.exactIntegrients.put(key, new ItemStack(ingredient));
        return this;
    }

    @Override
    public CustomRecipe setIngredient(char key, MaterialData ingredient) {
        super.setIngredient(key, ingredient);
        this.exactIntegrients.put(key, new ItemStack(ingredient.getItemType(), 1, ingredient.getData()));
        return this;
    }

    @Override
    public CustomRecipe setIngredient(char key, Material ingredient, int raw) {
        super.setIngredient(key, ingredient, raw);
        this.exactIntegrients.put(key, new ItemStack(ingredient, 1, (short) raw));
        return this;
    }

    public CustomRecipe setIngredient(char key, ItemStack item) {
        super.setIngredient(key, item.getType(), item.getDurability());
        this.exactIntegrients.put(key, item);
        return this;
    }
  
    public boolean equals(ItemStack[] matrix) {
        String[] shape = super.getShape();
        for (int y = 0; y < shape.length; y++) {
            String line = shape[y];
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                int i = y * line.length() + x;      
                ItemStack item0 = matrix[i];
                ItemStack item1 = this.exactIntegrients.get(c);
                if (item0 != null && item1 != null) {
                    if (item0.getTypeId() != item1.getTypeId() || item0.getDurability() != item1.getDurability() || !item0.getItemMeta().equals(item1.getItemMeta())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
  
    public void register() {
        Bukkit.addRecipe(this);
        ItemStack result = super.getResult();
        List<CustomRecipe> list = CustomRecipe.recipes.get(result);
        if (list == null) {
            list = new ArrayList<CustomRecipe>();
        }
        if (!list.contains(this)) {
            list.add(this);
            CustomRecipe.recipes.put(result, list);
        }
    }
  
    public static List<CustomRecipe> getRecipes(ItemStack item) {
        return CustomRecipe.recipes.get(item);
    }
  
    public static CustomRecipe process(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();
        ItemStack result = inv.getResult();
        List<CustomRecipe> recipes = CustomRecipe.getRecipes(result);
        if (recipes != null) {
            inv.setResult(null);
            for (CustomRecipe recipe : recipes) {
                if (recipe.equals(inv.getMatrix())) {
                    inv.setResult(recipe.getResult());
                    return recipe;
                }
            }
        }
        return null;
    }
    
	public void addRecipes(ItemStack result, String a, String b, String c, Character key, ItemStack ingredient) {
		CustomRecipe recipe = new CustomRecipe(result);
		recipe.shape(a, b, c);
		recipe.setIngredient(key, ingredient);
		recipe.register();
	}
	
	public void addRecipes(ItemStack result, String a, String b, String c, Character key, ItemStack ingredient, Character key2, ItemStack ingedient2) {
		CustomRecipe recipe = new CustomRecipe(result);
		recipe.shape(a, b, c);
		recipe.setIngredient(key, ingredient);
		recipe.setIngredient(key2, ingedient2);
		recipe.register();
	}
}