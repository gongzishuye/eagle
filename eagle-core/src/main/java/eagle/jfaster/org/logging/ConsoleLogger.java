package eagle.jfaster.org.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by fangyanpeng 2017/08/14
 */
public class ConsoleLogger extends AbstractInternalLogger {

  protected ConsoleLogger(String name) {
    super(name);
  }

  @Override
  public boolean isTraceEnabled() {
    return true;
  }

  @Override
  public void trace(String msg) {
    println(msg);
  }

  @Override
  public void trace(String format, Object arg) {
    FormattingTuple ft = MessageFormatter.format(format, arg);
    println4FormattingTuple(ft);
  }

  @Override
  public void trace(String format, Object argA, Object argB) {
    FormattingTuple ft = MessageFormatter.format(format, argA, argB);
    println4FormattingTuple(ft);
  }

  @Override
  public void trace(String format, Object... arguments) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
    println4FormattingTuple(ft);
  }

  @Override
  public void trace(String msg, Throwable t) {
    println(msg, t);
  }

  @Override
  public boolean isDebugEnabled() {
    return isTraceEnabled();
  }

  @Override
  public void debug(String msg) {
    trace(msg);
  }

  @Override
  public void debug(String format, Object arg) {
    trace(format, arg);
  }

  @Override
  public void debug(String format, Object argA, Object argB) {
    trace(format, argA, argB);
  }

  @Override
  public void debug(String format, Object... arguments) {
    trace(format, arguments);
  }

  @Override
  public void debug(String msg, Throwable t) {
    trace(msg, t);
  }

  @Override
  public boolean isInfoEnabled() {
    return isTraceEnabled();
  }

  @Override
  public void info(String msg) {
    trace(msg);
  }

  @Override
  public void info(String format, Object arg) {
    trace(format, arg);
  }

  @Override
  public void info(String format, Object argA, Object argB) {
    trace(format, argA, argB);
  }

  @Override
  public void info(String format, Object... arguments) {
    trace(format, arguments);
  }

  @Override
  public void info(String msg, Throwable t) {
    trace(msg, t);
  }

  @Override
  public boolean isWarnEnabled() {
    return isTraceEnabled();
  }

  @Override
  public void warn(String msg) {
    trace(msg);
  }

  @Override
  public void warn(String format, Object arg) {
    trace(format, arg);
  }

  @Override
  public void warn(String format, Object... arguments) {
    trace(format, arguments);
  }

  @Override
  public void warn(String format, Object argA, Object argB) {
    trace(format, argA, argB);
  }

  @Override
  public void warn(String msg, Throwable t) {
    trace(msg, t);
  }

  @Override
  public boolean isErrorEnabled() {
    return isTraceEnabled();
  }

  @Override
  public void error(String msg) {
    trace(msg);
  }

  @Override
  public void error(String format, Object arg) {
    trace(format, arg);
  }

  @Override
  public void error(String format, Object argA, Object argB) {
    trace(format, argA, argB);
  }

  @Override
  public void error(String format, Object... arguments) {
    trace(format, arguments);
  }

  @Override
  public void error(String msg, Throwable t) {
    trace(msg, t);
  }

  private void println(String msg) {
    System.out.println(formatDate(new Date()) + " [" + Thread.currentThread() + "] " + msg);
  }

  private void println(String msg, Throwable t) {
    System.err.println(formatDate(new Date()) + " [" + Thread.currentThread() + "] " + msg);
    t.printStackTrace();
  }

  private void println4FormattingTuple(FormattingTuple ft) {
    if (ft.getThrowable() != null) {
      ft.getThrowable().printStackTrace();
    } else {
      println(ft.getMessage());
    }
  }

  private String formatDate(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
    return format.format(date);
  }

}
