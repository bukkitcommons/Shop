package cc.bukkit.shop.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.google.common.collect.Queues;
import cc.bukkit.shop.util.ShopLogger;

/** Queued database manager. Use queue to solve run SQL make server lagg issue. */
public class Dispatcher implements Runnable {
  private final BlockingQueue<PreparedStatement> sqlQueue = Queues.newLinkedBlockingQueue();

  @NotNull
  private final Database database;
  @Nullable
  private volatile boolean running = true;
  @NotNull
  private final Thread dispatcherThread;

  /**
   * Queued database manager. Use queue to solve run SQL make server lagg issue.
   *
   * @param plugin plugin main class
   * @param db database
   */
  public Dispatcher(@NotNull Database db) {
    database = db;
    
    dispatcherThread = new Thread(this);
    dispatcherThread.setName("Shop Database Dispatcher");
    dispatcherThread.setPriority(Thread.MIN_PRIORITY);
    dispatcherThread.start();
  }

  /**
   * Add preparedStatement to queue waiting flush to database,
   *
   * @param ps The ps you want add in queue.
   */
  public void add(@NotNull PreparedStatement ps) {
    if (running)
      sqlQueue.offer(ps);
    else
      execute(ps);
  }

  @Override
  public void run() {
    try {
      while (running)
        execute(sqlQueue.take());
    } catch (InterruptedException interrupted) {
      running = false;
    }
  }
  
  private void execute(PreparedStatement statement) {
    try {
      statement.execute();
    } catch (SQLException sql) {
      sql.printStackTrace();
    }

    try {
      statement.close();
    } catch (SQLException sql) {
      sql.printStackTrace();
    }
  }
  
  public void close() {
    dispatcherThread.interrupt();
    ShopLogger.instance().info("Please wait for the data to flush...");
    sqlQueue.forEach(statement -> execute(statement));
    sqlQueue.clear();
    
    try {
      database.getConnector().close();
    } catch (Throwable t) {
      ;
    }
  }
}
