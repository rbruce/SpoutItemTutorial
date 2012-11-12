package edu.unca.rbruce.SpoutItemTutorial;

import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

/*
 * This is the main class of the sample plug-in
 */
public class SpoutItemTutorial extends JavaPlugin {
	public TestItem Quaffle;
	public SpoutItemTutorialCommandExecutor executor;

	/*
	 * This is called when your plug-in is enabled
	 */
	@Override
	public void onEnable() {

		// from Spout tutorial
		SpoutManager.getFileManager().addToPreLoginCache(this,
				"http://img99.imageshack.us/img99/7586/quaffle.png");
		Quaffle = new TestItem(this, "Quaffle",
				"http://img99.imageshack.us/img99/7586/quaffle.png");
		addQuaffleRecipe();

		getLogger().log(Level.INFO, "[Spout Item Test Plugin] Enabled!");

		// save the configuration file
		saveDefaultConfig();

		// Create the SampleListener
		new SpoutItemTutorialListener(this);

		// set the command executor for sample
		executor = new SpoutItemTutorialCommandExecutor(this);
		this.getCommand("message").setExecutor(executor);
		this.getCommand("changeMe").setExecutor(executor);
		this.getCommand("changeMeBack").setExecutor(executor);
	}

	/*
	 * This is called when your plug-in shuts down
	 */
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().log(Level.INFO, "[Spout Item Test Plugin] Disabled");
	}

	public void addQuaffleRecipe() {
		SpoutItemStack item = new SpoutItemStack(Quaffle, 1);
		SpoutShapedRecipe recipe = new SpoutShapedRecipe(item);
		recipe.shape(" X ", "X X", " X ");// top : middle : bottom
		recipe.setIngredient('X', MaterialData.leather);
		SpoutManager.getMaterialManager().registerSpoutRecipe(recipe);
	}

}
