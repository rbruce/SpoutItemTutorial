package edu.unca.rbruce.SpoutItemTutorial;

import java.util.logging.Logger;

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
	public final Logger logger = Logger.getLogger("Minecraft");
	public TestItem Quaffle;

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
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion()
				+ " has been enabled.");
		addQuaffleRecipe();

		// save the configuration file
		saveDefaultConfig();

		// Create the SampleListener
		new SpoutItemTutorialListener(this);

		// set the command executor for sample
		this.getCommand("sample").setExecutor(
				new SpoutItemTutorialCommandExecutor(this));
	}

	/*
	 * This is called when your plug-in shuts down
	 */
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " is now disabled!");
	}

	public void addQuaffleRecipe() {
		SpoutItemStack item = new SpoutItemStack(Quaffle, 1);
		SpoutShapedRecipe recipe = new SpoutShapedRecipe(item);
		recipe.shape(" X ", "X X", " X ");// top : middle : bottom
		recipe.setIngredient('X', MaterialData.leather);
		SpoutManager.getMaterialManager().registerSpoutRecipe(recipe);
	}

}
