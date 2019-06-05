public class Main extends JavaPlugin{
	
	@Override 
	public void onEnable() {
		
		getLogger().info("Custom Commands has been enabled.");
		PluginManager pm = getServer().getPluginManager();
		CCListener listener = new CCListener(this);
		pm.registerEvents(listener, this);
		
	}
		
	@Override 
	public void onDisable() {
		
		getLogger().info("Custom Commands has been disabled.");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if (sender instanceof Player ) {
			
			String lowerCmd = cmd.getName().toLowerCase();
			
			switch (lowerCmd) {
			
				case "spawn":
					
					World world = Bukkit.getServer().getWorld("world");
					Location loc = new Location(world, -114.464, 62, 234.638);
			
					player.teleport(loc);
					return true;
				
				default:
					//error message
					player.sendMessage("Your Command was not recognised!");
					return true;			
			
			}
			
					}
		
		player.sendMessage("Your Command was not recognised!");
		return true;
		
		
		
		
	}
	
}
