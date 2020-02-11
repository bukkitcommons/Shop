package cc.bukkit.shop.command;

import java.util.List;

public interface CommandData {
  CommandProcesser executor();
  
  boolean hidden();
  
  List<String> permissions();
  
  String label();
}
